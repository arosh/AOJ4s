#!/usr/bin/env ruby

class String
  def to_pascal_case
    self.split("_").map {|s| s.capitalize }.join
  end
end

def scala_doc_comment(s)
  return "/** #{s} */"
end

def query_package
  def generate(class_name, wrapper_name)
    return <<-"EOS"
package com.github.arosh.aoj4s

package object query {

  sealed abstract class Query[A](val field: String) {
    def value: A
  }

  #{class_name.join("\n\n  ")}

  trait QueryWrapper {

    #{wrapper_name.join("\n\n    ")}

  }
}
    EOS
  end

  source = <<-'EOS'
criteria    Int
affiliation String
solved_min  Int
solved_max  Int
user_id     String
problem_id  String
language    String
date_begin  Long
date_end    Long
start       Int
limit       Int
id          String
category    String
  EOS

  queries = source.each_line.map {|line|
    name, type = line.chomp.split(/ +/)
    upper_name = name.to_pascal_case
    # upper_name = name.split("_").map {|s| s.capitalize }.join
    [upper_name, type, name]
  }

  class_name = queries.map {|line|
    "case class #{line[0]}(value: #{line[1]}) extends Query[#{line[1]}](\"#{line[2]}\")"
  }

  wrapper_name = queries.map {|line|
    "implicit def Wrap#{line[0]}(value: #{line[1]}) = #{line[0]}(value)"
  }

  puts generate(class_name, wrapper_name)
end

def category
  def generate(category_list)
    return <<-"EOS"
package com.github.arosh.aoj4s

object CATEGORIES {

  #{category_list.join("\n\n  ")}

}
    EOS
  end

  source = <<-'EOS'
SF straight       Straightforward
DM datamanipu     Data Manipulation/Sort/Search
SM simulation     Simulation
ST string         String Manipulation
PR parsing        Parsing
PZ puzzle         Puzzle
CG geometry       Computational Geometry
GR graph          Graph
NS numeric        Numeric Problems/Simulation
CP combinatorial  Combinatorial Problem
NT number         Number Theory
PB probability    Probability
  EOS

  list = source.each_line.map {|line|
    /^(\w{2}) (\w+)\s+(.+)$/.match(line.chomp)
  }

  category_list = list.map {|line|
    "#{scala_doc_comment(line[3])}\n  val #{line[1]} = \"#{line[2]}\""
  }

  puts generate(category_list)

end

def xml_info(_name, source)
  def generate(name, s)
    return <<-"EOS"
package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class #{name.to_pascal_case}(#{name}XML: Elem) {

#{s}

}
    EOS
  end

  only = []
  many = []
  normal = []

  source.size.times do |i|
    if source[i] =~ /^only\s+(\w+)\s+(.+)/
      # only
      pname = $1
      pcomment = $2
      tmp = []

      i += 1
      while(i < source.size && source[i] =~ /^(?:\t|  )(\w+)\s+(\w+)\s+(.+)/)
        name = $1
        type = $2
        comment = $3
        tmp.push [name, type, comment]
        i += 1
      end

      only.push [pname, pcomment, tmp]

    elsif source[i] =~ /^many\s+(\w+)\s+(\w+)\s+(.+)/
      # many
      pname = $1
      pinner = $2
      pcomment = $3
      tmp = []

      i += 1
      while(i < source.size && source[i] =~ /^(?:\t|  )(\w+)\s+(\w+)\s+(.+)/)
        name = $1
        type = $2
        comment = $3
        tmp.push [name, type, comment]
        i += 1
      end

      many.push [pname, pinner, pcomment, tmp]

    elsif source[i] =~ /^(\w+)\s+(\w+)\s+(.+)/
      # elem
      name = $1
      type = $2
      comment = $3

      normal.push [name, type, comment]
    end

  end

  # p only
  # p many
  # p normal

  xml_name = _name + "XML"

  normalsc = normal.map {|elem|
    "#{scala_doc_comment(elem[2])}\n" +
    "lazy val #{elem[0]}: #{elem[1]} = (#{xml_name} \\ \"#{elem[0]}\").text" +
          (elem[1] == "String" ? "" : ".to#{elem[1]}")
  }.join("\n\n")

  onlysc = only.map {|par|
    codebox = []
    codebox.push "#{scala_doc_comment(par[1])}\n" +
                    "lazy val #{par[0]} = #{par[0].to_pascal_case}Struct(#{xml_name} \\ \"#{par[0]}\")"

    elem_xml_name = "#{par[0]}XML"
    codebox.push "case class #{par[0].capitalize}Struct(#{elem_xml_name}: NodeSeq) {"


    par[2].each do |elem|
      codebox.push "  #{scala_doc_comment(elem[2])}\n" +
                   "  lazy val #{elem[0]}: #{elem[1]} = (#{elem_xml_name} \\ \"#{elem[0]}\").text" +
                        (elem[1] == "String" ? "" : ".to#{elem[1]}")
    end

    codebox.push "}"
    codebox.join("\n\n")
  }


  manysc = many.map {|par|
    codebox = []
    codebox.push "#{scala_doc_comment(par[2])}\n" +
                  "lazy val #{par[0]} = #{xml_name} \\ \"#{par[0]}\" \\ \"#{par[1]}\" map (x => #{par[1].to_pascal_case}Struct(x))"

    elem_xml_name = "#{par[1]}XML"
    codebox.push "case class #{par[1].to_pascal_case}Struct(#{elem_xml_name}: NodeSeq) {"

    par[3].each do |elem|
      codebox.push "  #{scala_doc_comment(elem[2])}\n" +
                   "  lazy val #{elem[0]}: #{elem[1]} = (#{elem_xml_name} \\ \"#{elem[0]}\").text" +
                    (elem[1] == "String" ? "" : ".to#{elem[1]}")

    end

    codebox.push "}"
    codebox.join("\n\n")
  }

  puts generate(_name, [normalsc, onlysc, manysc].join("\n\n").each_line.map {|s| "  " + s}.join)

end

def gen_user
  source = <<-'EOS'
id	String	User ID.
name	String	User Name.
affiliation	String	Affiliation of the user.
registerdate	Long	Registered date.
lastsubmitdate	Long	Last submitted date.
only status		User's status
  submission	Int	 The number of submissions.
  solved	Int	 The number of solved problems.
  accepted	Int	 The number of accepted submissions.
  wronganswer	Int	 The number of wrong answers.
  timelimit	Int	 The number of time limit exceeding.
  memorylimit	Int	 The number of memory limit exceeding.
  outputlimit	Int	 The number of output limit exceeding.
  runtimeerror	Int	 The number of runtime errors.
  compileerror	Int	 The number of compile errors.
many solved_list problem		List of solved problems
  id	String	 Problem ID.
  submissiondate	Long	Date of submission.
  language	String	Programming Language.
  cputime	Int	CPU Time (sentisecond).
  memory	Int	Memory usage (Kbyte).
  code_size	Int	Code size (byte).
  EOS
  xml_info("user", source.split("\n"))
end

def gen_problem
  source = <<-'EOS'
id	String	Problem ID.
name	String	Problem Name.
available	Int	Judge Type (0:Not available, 1:Judge, 2:Judge allowing precision error, 3:Judge with Validator, 4:Reactive judge).
problemtimelimit	Int	Time limit assigned to the problem (second).
problemmemorylimit	Int	Memory limit assigned to the problem (Kbyte).
only status		Problem's status
  submission	Int	 The number of submissions.
  accepted	Int	 The number of accepted submissions.
  wronganswer	Int	 The number of wrong answers.
  timelimit	Int	 The number of time limit exceeding.
  memorylimit	Int	 The number of memory limit exceeding.
  outputlimit	Int	 The number of output limit exceeding.
  runtimeerror	Int	 The number of runtime errors.
many solved_list user		List of users who solved the problem
  id	String	 User ID.
  submissiondate	Long	Date of submission.
  language	String	Programming Language.
  cputime	Int	CPU Time (sentisecond).
  memory	Int	Memory usage (Kbyte).
  code_size	Int	Code size (byte).
  EOS
  xml_info("problem", source.split("\n"))
end

def gen_problem_list
  source = <<-'EOS'
many problem_list problem		List of problems in the specified volume
  id	String	 Problem ID.
  name	String	Name of the problem.
  problemtimelimit	Int	Time limit assigned to the problem (second).
  problemmemorylimit	Int	Memory limit assigned to the problem (Kbyte).
  EOS
  xml_info("problem_list", source.split("\n"))
end

def gen_user_list
  source = <<-'EOS'
many user_list user		List of users
  rank	Int	 The rank based on the specified criteria.
  id	String	 User ID.
  name	String	 Name of the user.
  affiliation	String	 Affiliation of the user.
  solved	Int	 The number of solved problems.
  rating	Float	 Rating of the user.  
  EOS
  xml_info("user_list", source.split("\n"))
end

def gen_sovled_record
  source = <<-'EOS'
many solved_record_list solved		List of solved records
  run_id	Int	 Run ID.
  user_id	String	 User ID.
  problem_id	String	 Problem ID.
  date	Long	 Date of submission.
  language	String	 Programming language.
  cputime	Int	 CPU Time (sentisecond).
  memory	Int	 Memory usage (Kbyte).
  code_size	Int	 Code size (byte).
  EOS
  xml_info("solved_record", source.split("\n"))
end

def gen_status_log
  source = <<-'EOS'
many status_list status		List of status records
  run_id	Int	 Run ID.
  user_id	String	 User ID.
  problem_id	String	 Problem ID.
  submission_date	Long	 Date of submission.
  status	String	 Judge status.
  language	String	 Programming language.
  cputime	Int	 CPU Time (sentisecond).
  memory	Int	 Memory usage (Kbyte).
  code_size	Int	 Code size (byte).
  EOS
  xml_info("status_log", source.split("\n"))
end

def gen_problem_category
  source = <<-'EOS'
many problem_category problem		List of problems.
  id	String	Problem ID.
  category	String	Category Name.
  score	Float	Score assigned to the problem.
  EOS
  xml_info("problem_category", source.split("\n"))
end

def gen_source
  source = <<-'EOS'
id	String	Problem ID.
title	String	Title of the source.
subtitle	String	Subtitle of the source.
place	String	The place the corresponding contest.
abbr	String	Abbreviation of the title.
author1	String	Author of the problem.
author2	String	Second author of the problem.
year	String	Year
month	String	Month
day	String	Day
note	String	Special notes
url	String	URL
judge	String	Judge
  EOS
  xml_info("source", source.split("\n"))
end

gen_source


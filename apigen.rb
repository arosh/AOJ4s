#!/usr/bin/env ruby

def info_package
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
    upper_name = name.split("_").map {|s| s.capitalize }.join
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
    "/**#{line[3]} */\n  val #{line[1]} = \"#{line[2]}\""
  }

  puts generate(category_list)

end

# info_package
category



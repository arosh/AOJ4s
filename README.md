What's this?
==========

This is AOJ4s, an API wrapper for [AIZU ONLINE JUDGE](http://judge.u-aizu.ac.jp/onlinejudge/) written in [Scala](http://www.scala-lang.org/).

Example
==========
	import jp.gr.java_conf.shora_kujira16.AOJ4s.AOJ._

	object Main extends App {
	  SLEEP_TIME = 1000
	  println(user_list(solved_min = 500).user.map(_.id))
	}

	>> List(K_Operafan, s1150092, lyrically, shioshiota, Respect2D, laycurse, yukim, fura2, sune2, ishikado, ee67052, mamekin, rankalee)


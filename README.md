What's this?
==========

This is AOJ4s, an API wrapper for [AIZU ONLINE JUDGE](http://judge.u-aizu.ac.jp/onlinejudge/) written in [Scala](http://www.scala-lang.org/).

Example
==========
	import com.github.arosh.AOJ4s.AOJ._

	SLEEP_TIME = 1000

	user_list(solved_min = 500).get.user.map { _.id }

	>> List(K_Operafan, s1150092, sune2, yukim, ishikado, lyrically, fura2, ee67052, Respect2D, shioshiota, laycurse, yutaka, mamekin, kyuridenamida, rankalee, blue_jam)


What's this?
==========

This is AOJ4s, an API wrapper for [AIZU ONLINE JUDGE](http://judge.u-aizu.ac.jp/onlinejudge/) written in [Scala](http://www.scala-lang.org/).

Example
==========
	import com.github.arosh.aoj4s.AOJ._

	implicit val st: SleepTime = 1000

	user_list(solved_min = 500).get.user.map { _.id }
	>> List(K_Operafan, s1150092, sune2, yukim, ishikado, lyrically, fura2, ee67052, Respect2D, shioshiota, laycurse, yutaka, mamekin, kyuridenamida, rankalee, blue_jam)

	solved_record("sho518").get.solved map { _.problem_id }
	>> List(0000, 0001, 0002, 0003, 0004, 0005, 0006, 0007, 0008, 0009, 0011, 0012, 0013, 0014, 0015, 0016, 0017, 0018, 0019, 0020, 0021, 0022, 0023, 0024, 0025, 0026, 0027, 0028, 0029, 0030, 0031, 0032, 0033, 0034, 0036, 0038, 0039, 0042, 0044, 0045, 0046, 0047, 0048, 0049, 0050, 0051, 0052, 0053, 0054, 0055, 0056, 0057, 0058, 0059, 0060, 0061, 0062, 0063, 0064, 0065, 0066, 0067, 0073, 0075, 0077, 0078, 0083, 0084, 0087, 0100, 0101, 0102, 0103, 0104, 0105, 0108, 0126, 0127, 0151, 0156, 0200, 0205, 0206, 0216, 0217, 0218, 0219, 0222, 0226, 0227, 0229, 0500, 0501, 0502, 0505, 0506, 0507, 0510, 0511, 0512, 0513, 0515, 0516, 0521, 0522, 0526, 0532, 0533, 0534, 0543, 0545, 0546, 0554, 0555, 0556, 0557, 0558, 1000, 1004, 1009, 1017, 1018, 1019, 1027, 1...
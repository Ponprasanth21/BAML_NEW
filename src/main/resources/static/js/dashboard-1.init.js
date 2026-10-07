$(document).ready(
		function() {
			var o, e = function() {
				$("#sparkline1").sparkline(
						[ 0, 23, 43, 35, 44, 45, 56, 37, 40 ], {
							type : "line",
							width : "100%",
							height : 210,
							chartRangeMax : 50,
							lineColor : "#3bafda",
							fillColor : "rgba(59,175,218,0.3)",
							highlightLineColor : "rgba(0,0,0,.1)",
							highlightSpotColor : "rgba(0,0,0,.2)",
							maxSpotColor : !1,
							minSpotColor : !1,
							spotColor : !1,
							lineWidth : 1
						}), $("#sparkline1").sparkline(
						[ 25, 23, 26, 24, 25, 32, 30, 24, 19 ], {
							type : "line",
							width : "100%",
							height : "210",
							chartRangeMax : 40,
							lineColor : "#1abc9c",
							fillColor : "rgba(26, 188, 156, 0.3)",
							composite : !0,
							highlightLineColor : "rgba(0,0,0,.1)",
							highlightSpotColor : "rgba(0,0,0,.2)",
							maxSpotColor : !1,
							minSpotColor : !1,
							spotColor : !1,
							lineWidth : 1
						})
			};
			e(), $(window).resize(function(i) {
				clearTimeout(o), o = setTimeout(function() {
					e()
				}, 300)
			})
		});
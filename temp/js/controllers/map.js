/**
 * Copyright 2016 Zeus, All Rights Reserved
 * map.js
 * 
 */

'use strict';

angular.module('ZeusApp').controller(
	'mapCtrl', ['$scope', '$state', '$stateParams', '$timeout', 'appConstant', mapCtrl]);

function mapCtrl($scope, $state, $stateParams, $timeout, appConstant) {

	$("#container").height($(window).height()-86);
	$("#container").width($(window).width() - 380);

	/**
	 * 年度告警统计
	 */
	$scope.loadWarningYear = function(barInfo) {
		$timeout(function() {
			$('#Warning-Bar').highcharts({
				chart: {
					type: 'column',
					backgroundColor: '#152d44'
				},
				title: {
					text: ''
				},
				xAxis: {
					categories: ['1级', '2级', '3级'],
					tickColor: '#FF0000',
					tickWidth: 1,
					labels: {
						style: {
							color: '#FFF' // 坐标上数字颜色
						}
					}
				},
				yAxis: {
					min: 0,
					title: {
						text: '告警次数',
						style: {
							color: '#FFF'
						}
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: '#FFF' // 柱状上面数字颜色
						}
					},
					labels: {
						style: {
							color: '#FFF' // 坐标上数字颜色
						}
					}
				},
				legend: {
					align: 'right',
					x: -30,
					verticalAlign: 'top',
					y: 25,
					floating: true,
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#152d44',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false,
					itemStyle: {
						color: '#FFF'
					},
					itemHoverStyle: {
						color: '#CCC'
					}
				},
				tooltip: {
					formatter: function() {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y + '<br/>' +
							'总计: ' + this.point.stackTotal;
					}
				},
				plotOptions: {
					series: {
						borderWidth: 0
					},
					column: {
						stacking: 'normal',
						dataLabels: {
							enabled: false,
							color: '#FFF', // 柱状里面数字颜色
							style: {
								textShadow: '0 0 3px black'
							}
						}
					}
				},
				series: [{
					name: barInfo[0].name,
					data: barInfo[0].data,
					color: "#03669e"
				}, {
					name: barInfo[1].name,
					data: barInfo[1].data,
					color: "#0a9ef1"
				}, {
					name: barInfo[2].name,
					data: barInfo[2].data,
					color: "#75c7f5"
				}, {
					name: barInfo[3].name,
					data: barInfo[3].data,
					color: "#bae1f7"
				}]
			});
		}, 10);
	};

	/**
	 * 月度告警统计
	 */
	$scope.loadWarningMonth = function(cirleInfo) {
		$timeout(function() {
			$('#eq-status-Bar').highcharts({
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false,
					backgroundColor: '#152d44'
				},
				title: {
					text: '',
					style: {
						color: '#FFF'
					}
				},
				tooltip: {
					pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				},
				legend: {
					backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#152d44',
					borderColor: '#CCC',
					borderWidth: 1,
					shadow: false,
					itemStyle: {
						color: '#FFF'
					},
					itemHoverStyle: {
						color: '#CCC'
					}
				},
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
							enabled: true,
							format: '<b>{point.name}</b>: {point.percentage:.1f} %',
							style: {
								color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'white'
							}
						},
						showInLegend: true
					}
				},
				series: [{
					type: 'pie',
					name: '告警概率',
					data: [{
						name: cirleInfo[0].name,
						y: cirleInfo[0].data,
						color: "#03669e"
					}, {
						name: cirleInfo[1].name,
						y: cirleInfo[1].data,
						color: "#0a9ef1"
					}, {
						name: cirleInfo[2].name,
						y: cirleInfo[2].data,
						color: "#75c7f5"
					}, {
						name: cirleInfo[3].name,
						y: cirleInfo[3].data,
						color: "#bae1f7"
					}]
				}]
			});
		}, 10);
	};

	/**
	 * 流量信息
	 */
	$scope.loadFlowStatus = function() {
		$timeout(function() {
			Highcharts.setOptions({
				global: {
					useUTC: false
				}
			});
			$('#flow-Bar').highcharts({
				chart: {
					type: 'spline',
					animation: Highcharts.svg, // don't animate in old IE
					marginRight: 10,
					backgroundColor: '#152d44',
					events: {
						load: function() {
							// set up the updating of the chart each second
							var series = this.series[0];
							setInterval(function() {
								var x = (new Date()).getTime(), // current time
									y = Math.random();
								series.addPoint([x, y], true, true);
							}, 1000);
						}
					}
				},
				title: {
					text: '实时流量信息',
					style: {
						color: '#FFF'
					}
				},
				xAxis: {
					type: 'datetime',
					tickPixelInterval: 150,
					labels: {
						style: {
							color: '#FFF' // 坐标上数字颜色
						}
					}
				},
				yAxis: {
					title: {
						text: ''
					},
					labels: {
						style: {
							color: '#FFF' // 坐标上数字颜色
						}
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#fff'
					}]
				},
				tooltip: {
					formatter: function() {
						return '<b>' + this.series.name + '</b><br/>' +
							Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
							Highcharts.numberFormat(this.y, 2);
					}
				},
				legend: {
					enabled: false,
					itemStyle: {
						color: '#FFF'
					},
					itemHoverStyle: {
						color: '#CCC'
					}
				},
				exporting: {
					enabled: false
				},
				series: [{
					name: 'Random data',
					data: (function() {
						// generate an array of random data
						var data = [],
							time = (new Date()).getTime(),
							i;
						for(i = -19; i <= 0; i += 1) {
							data.push({
								x: time + i * 1000,
								y: Math.random()
							});
						}
						return data;
					}())
				}]
			});
		})
	};

	/**
	 * 加载地图
	 */
	$scope.loadMap = function() {
		$timeout(function() {
			var dom = document.getElementById("container");

			var myChart = echarts.init(dom);
			var app = {};
			var option = null;
			var data = [{
				name: '衢州',
				value: 177
			}, {
				name: '廊坊',
				value: 193
			}, {
				name: '菏泽',
				value: 194
			}, {
				name: '合肥',
				value: 229
			}, {
				name: '武汉',
				value: 273
			}, {
				name: '大庆',
				value: 279
			}];
			var geoCoordMap = {
				'衢州': [118.88, 28.97],
				'廊坊': [116.7, 39.53],
				'菏泽': [115.480656, 35.23375],
				'合肥': [117.27, 31.86],
				'武汉': [114.31, 30.52],
				'大庆': [125.03, 46.58]
			};

			var convertData = function(data) {
				var res = [];
				for(var i = 0; i < data.length; i++) {
					var geoCoord = geoCoordMap[data[i].name];
					if(geoCoord) {
						res.push({
							name: data[i].name,
							value: geoCoord.concat(data[i].value)
						});
					}
				}
				return res;
			};

			option = {
				tooltip: {
					trigger: 'item'
				},
				legend: {
					orient: 'vertical',
					y: 'bottom',
					x: 'right',
					textStyle: {
						color: '#fff'
					}
				},
				geo: {
					map: 'china',
					label: {
						emphasis: {
							show: false
						}
					},
					roam: true,
					itemStyle: {
						normal: {
							areaColor: 'rgba(11, 79, 125,1)',
							borderColor: '#111'
						},
						emphasis: {
							areaColor: 'rgba(11, 79, 125, 0.5)',
						}
					}
				},
				series: [{
					name: '',
					type: 'scatter',
					coordinateSystem: 'geo',
					data: convertData(data),
					symbolSize: function(val) {
						return val[2] / 10;
					},
					label: {
						normal: {
							formatter: '{b}',
							position: 'right',
							show: false
						},
						emphasis: {
							show: true
						}
					},
					itemStyle: {
						normal: {
							color: '#ddb926'
						}
					}
				}, {
					name: '监控信息',
					type: 'effectScatter',
					coordinateSystem: 'geo',
					data: convertData(data.sort(function(a, b) {
						return b.value - a.value;
					}).slice(0, 6)),
					symbolSize: function(val) {
						return val[2] / 10;
					},
					showEffectOn: 'render',
					rippleEffect: {
						brushType: 'stroke'
					},
					hoverAnimation: true,
					label: {
						normal: {
							formatter: '{b}',
							position: 'right',
							show: true
						}
					},
					itemStyle: {
						normal: {
							color: '#f4e925',
							shadowBlur: 10,
							shadowColor: '#333'
						}
					},
					zlevel: 1
				}]
			};;

			if(option && typeof option === "object") {
				myChart.setOption(option, true);
			}

			myChart.on('click', function(params) {
				$state.go('app.menu.jtopo');
			});
		}, 100);
	};

	(function() {
		// 加载年度告警统计
		$.ajax({
			url: appConstant.BaseUrl + 'warning-Year.json',
			type: 'get',
			dataType: "json",
			success: function(response) {
				$scope.loadWarningYear(response.data);
			},
			error: function(e) {}
		})

		// 加载月度告警统计
		$.ajax({
			url: appConstant.BaseUrl + 'warningMonth.json',
			type: 'get',
			dataType: "json",
			success: function(response) {
				$scope.loadWarningMonth(response.data);
			},
			error: function(e) {}
		})
		$scope.loadFlowStatus();
		$scope.loadMap();
	})();
}
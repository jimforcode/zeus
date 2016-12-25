/**
 * Copyright 2016 Zeus, All Rights Reserved
 * orderManger.js
 * 
 */

'use strict';

angular.module('ZeusApp').controller(
	'initCtrl', ['$scope', '$state', '$stateParams', '$interval', '$timeout', 'appConstant', initCtrl]);

function initCtrl($scope, $state, $stateParams, $interval, $timeout, appConstant) {

	/**
	 * 年度告警统计
	 */
	$scope.loadWarningYear = function(barInfo) {
		$timeout(function() {
			$('#WarningBar').highcharts({
				chart: {
					type: 'column',
					backgroundColor: '#152d44'
				},
				title: {
					text: ''
				},
				xAxis: {
					categories: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
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
			$('#WarningCircle').highcharts({
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false,
					backgroundColor: '#152d44'
				},
				title: {
					text: '当前月度',
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
	 * 本机服务器信息
	 */
	$scope.loadServerInfo = function() {
		$timeout(function() {
			var gaugeOptions = {
				chart: {
					type: 'solidgauge',
					backgroundColor: '#152d44'
				},
				title: null,
				pane: {
					center: ['50%', '85%'],
					size: '140%',
					startAngle: -90,
					endAngle: 90,
					background: {
						backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
						innerRadius: '60%',
						outerRadius: '100%',
						shape: 'arc'
					}
				},
				tooltip: {
					enabled: false
				},
				yAxis: {
					stops: [
						[0.1, '#55BF3B'], // green
						[0.5, '#DDDF0D'], // yellow
						[0.9, '#DF5353'] // red
					],
					lineWidth: 0,
					minorTickInterval: null,
					tickPixelInterval: 400,
					tickWidth: 0,
					title: {
						y: -70
					},
					labels: {
						y: 16,
						style: {
							color: '#FFF' // 坐标上数字颜色
						}
					},
				},
				plotOptions: {
					solidgauge: {
						dataLabels: {
							y: 5,
							borderWidth: 0,
							useHTML: true
						}
					}
				}
			};
			$('#Info-CPU').highcharts(Highcharts.merge(gaugeOptions, {
				yAxis: {
					min: 0,
					max: 100,
					title: {
						text: ''
					}
				},
				credits: {
					enabled: false
				},
				series: [{
					name: '使用率',
					data: [80],
					dataLabels: {
						format: '<div style="text-align:center"><span style="font-size:25px;color:' +
							((Highcharts.theme && Highcharts.theme.contrastTextColor) || '#FFF') + '">{y}</span><br/>' +
							'<span style="font-size:12px;color:silver">CPU使用率</span></div>'
					},
					tooltip: {
						valueSuffix: '百分比'
					}
				}]
			}));
			$('#Info-RAM').highcharts(Highcharts.merge(gaugeOptions, {
				yAxis: {
					min: 0,
					max: 100,
					title: {
						text: ''
					}
				},
				series: [{
					name: '使用率',
					data: [1],
					dataLabels: {
						format: '<div style="text-align:center"><span style="font-size:25px;color:' +
							((Highcharts.theme && Highcharts.theme.contrastTextColor) || '#FFF') + '">{y:.1f}</span><br/>' +
							'<span style="font-size:12px;color:silver">内存使用率</span></div>'
					},
					tooltip: {
						valueSuffix: '百分比'
					}
				}]
			}));

			$interval(function() {
				var chart = $('#Info-CPU').highcharts(),
					point,
					newVal,
					inc;
				if(chart) {
					point = chart.series[0].points[0];
					inc = Math.round((Math.random() - 0.5) * 100);
					newVal = point.y + inc;
					if(newVal < 0 || newVal > 100) {
						newVal = point.y - inc;
					}
					point.update(newVal);
				}
				chart = $('#Info-RAM').highcharts();
				if(chart) {
					point = chart.series[0].points[0];
					inc = Math.round((Math.random() - 0.5) * 100);
					newVal = point.y + inc;
					if(newVal < 0 || newVal > 100) {
						newVal = point.y - inc;
					}
					point.update(newVal);
				}
			}, 2000);

		}, 10);
	};

	(function() {
		$.ajax({
			url: appConstant.BaseUrl + 'wmessageInfo.json',
			type: 'get',
			dataType: "json",
			success: function(response) {
				$scope.wmessages = response.data;
			},
			error: function(e) {}
		})

		// 加载年度告警统计
		$.ajax({
			url: appConstant.BaseUrl + 'warningYear.json',
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

		// 加载服务器信息 
		$scope.loadServerInfo();

	})();

}
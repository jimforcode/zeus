/**
 * Copyright 2016 Zeus, All Rights Reserved
 * orderManger.js
 * 
 */

'use strict';

angular.module('ZeusApp').controller(
	'viewCtrl', ['$scope', '$state', '$mdSidenav', '$mdToast',
		'$mdDialog', '$stateParams', '$interval', viewCtrl
	]);

function viewCtrl($scope, $state, $mdSidenav, $mdToast, $mdDialog, $stateParams, $interval) {
	console.log($stateParams.ipAddress);
	(function() {})();

	setTimeout(function() {
		$('#CpuMonitor').highcharts({
			chart: {
				type: 'area',
				backgroundColor: '#152d44'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				categories: ['15：00', '15：30', '16：00', '16：30', '17：00', '17：30', '18：00'],
				tickmarkPlacement: 'on',
				title: {
					enabled: false
				},
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
				}
			},
			tooltip: {
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b> ({point.y:,.0f} millions)<br/>',
				shared: true
			},
			plotOptions: {
				area: {
					stacking: 'percent',
					lineColor: '#ffffff',
					lineWidth: 1,
					marker: {
						lineWidth: 1,
						lineColor: '#ffffff'
					}
				}
			},
			legend: {
				itemStyle: {
					color: '#FFF'
				},
				itemHoverStyle: {
					color: '#CCC'
				}
			},
			series: [{
				name: '%Wait',
				data: [502, 635, 809, 947, 1402, 3634, 5268]
			}, {
				name: '%System',
				data: [106, 107, 111, 133, 221, 767, 1766]
			}, {
				name: '%User',
				data: [163, 203, 276, 408, 547, 729, 628]
			}]
		});

		$('#DiskMonitor').highcharts({
			chart: {
				type: 'area',
				backgroundColor: '#152d44'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				categories: ['15：00', '15：30', '16：00', '16：30', '17：00', '17：30', '18：00'],
				tickmarkPlacement: 'on',
				title: {
					enabled: false
				},
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
					formatter: function() {
						return this.value + 'GB';
					},
					style: {
						color: '#FFF' // 坐标上数字颜色
					}
				}
			},
			tooltip: {
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b> ({point.y:,.0f} millions)<br/>',
				shared: true
			},
			legend: {
				itemStyle: {
					color: '#FFF'
				},
				itemHoverStyle: {
					color: '#CCC'
				}
			},
			plotOptions: {
				area: {
					lineColor: '#ffffff',
					lineWidth: 1,
					marker: {
						lineWidth: 1,
						lineColor: '#ffffff'
					}
				}
			},
			series: [{
				name: 'free',
				data: [931, 931, 931, 931, 931, 931, 931]
			}, {
				name: 'userd',
				data: [466, 466, 466, 466, 466, 466, 466]
			}]
		});

		$('#IOMonitor').highcharts({
			chart: {
				type: 'line',
				backgroundColor: '#152d44'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				categories: ['15：00', '15：30', '16：00', '16：30', '17：00', '17：30', '18：00'],
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
				}
			},
			plotOptions: {
				line: {
					enableMouseTracking: false
				}
			},
			legend: {
				itemStyle: {
					color: '#FFF'
				},
				itemHoverStyle: {
					color: '#CCC'
				}
			},
			series: [{
				name: 'rkb',
				data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2]
			}, {
				name: 'wkb',
				data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0]
			}]
		});

		$('#NetworkFlowMonitor').highcharts({
			chart: {
				type: 'line',
				backgroundColor: '#152d44'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				categories: ['15：00', '15：30', '16：00', '16：30', '17：00', '17：30', '18：00'],
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
					formatter: function() {
						return this.value + 'KB';
					},
					style: {
						color: '#FFF' // 坐标上数字颜色
					}
				}
			},
			plotOptions: {
				line: {
					enableMouseTracking: false
				}
			},
			legend: {
				itemStyle: {
					color: '#FFF'
				},
				itemHoverStyle: {
					color: '#CCC'
				}
			},
			series: [{
				name: 'rkb',
				data: [98, 87, 134, 154, 98, 87, 71]
			}, {
				name: 'wkb',
				data: [12, 32, 43, 38, 76, 55, 49]
			}]
		});

		$('#MemoryMonitor').highcharts({
			chart: {
				type: 'area',
				backgroundColor: '#152d44'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				categories: ['15：00', '15：30', '16：00', '16：30', '17：00', '17：30', '18：00'],
				tickmarkPlacement: 'on',
				title: {
					enabled: false
				},
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
				}
			},
			tooltip: {
				pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b> ({point.y:,.0f} millions)<br/>',
				shared: true
			},
			plotOptions: {
				area: {
					stacking: 'percent',
					lineColor: '#ffffff',
					lineWidth: 1,
					marker: {
						lineWidth: 1,
						lineColor: '#ffffff'
					}
				}
			},
			legend: {
				itemStyle: {
					color: '#FFF'
				},
				itemHoverStyle: {
					color: '#CCC'
				}
			},
			series: [{
				name: '%Wait',
				data: [56, 65, 46, 57, 68, 75, 88]
			}, {
				name: '%System',
				data: [21, 23, 43, 33, 48, 56, 76]
			}, {
				name: '%User',
				data: [43, 54, 33, 54, 65, 75, 89]
			}]
		});

	}, 10);
}
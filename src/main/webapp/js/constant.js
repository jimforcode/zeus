/**
 * Copyright 2016 YunLSP, All Rights Reserved
 * constant.js
 * 
 */

'use strict';

angular
  .module('ZeusApp')
  .constant('appConstant', {
    BaseUrl: 'http://localhost:8080/zeus/api/',
    httpStatus: {
      400: '',
      401: '',
      403: '',
      404: '',
      500: ''
    },
    error: {
      'default': {
        'message': ''
      }
    },
    alert: {},
    constant: {
      DATE_FORMAT: 'YYYY/MM/DD',
      MONTH_FORMAT: 'YYYY-MM'
    }
  });

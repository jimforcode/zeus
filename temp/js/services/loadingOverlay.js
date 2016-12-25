/**
 * Copyright 2016 YunLSP, All Rights Reserved
 */

angular
  .module('ZeusApp')
  .service('loadingOverlay',
    loadingOverlay
  );

function loadingOverlay($http, $compile, $document, $timeout) {
  (function() {
    if(angular.element('#_loadingOverlay_').length === 0) {
      var loader = $http.get('views/common/loading-overlay-template.html', {});
      var templateHtml;
      loader.success(function(html) {
        templateHtml = html;
      }).then(function (response) {
        var loadingOverlayElement = $compile(templateHtml)({});
        loadingOverlayElement.attr('id', '_loadingOverlay_');
        loadingOverlayElement.css('display', 'none');
        $document.find('body').eq(0).append(loadingOverlayElement);
      });
    }
  })();

  this.start = function() {
    $timeout(function(){
      var loadingOverlayElement = angular.element('#_loadingOverlay_');
      if(loadingOverlayElement.length > 0 && loadingOverlayElement.css('display') !== 'block') {
        loadingOverlayElement.css('display', 'block');
      }
    }, 0);
  };

  this.stop = function() {
    $timeout(function(){
      var loadingOverlayElement = angular.element('#_loadingOverlay_');
      if(loadingOverlayElement.length > 0 && loadingOverlayElement.css('display') !== 'none') {
        loadingOverlayElement.css('display', 'none');
      }
    }, 0);
  }
}
angular
  .module('ZeusApp')
  .directive('scrollSpy', ['$parse', scrollSpy])
  .directive('magnetsControl', ['$animate', magnetsControl]);

/**
 * scrollSpy - Directive for scroll event
 */
function scrollSpy($parse) {
  return {
    restrict: 'A',
    scope: false,
    link: function(scope, element, attrs) {
      var fn = $parse(attrs.scrollSpy);

      element.bind('scroll', function(ev) {
        scope.$apply(function() {
          fn(scope, {$scope: scope, $event: ev});
        });
      });
    }
  };
}

function magnetsControl($animate) {
  return {
    restrict: 'A',
    scope: false,
    link: function(scope, element, attrs) {
      var side = attrs.magnetsSide;
      var targetId = attrs.for;
      var targetElement = angular.element('#' + targetId);
      scope.$watchGroup([
        function() {
          return targetElement.css('display');
        },
        function() {
          return targetElement.css('width');
        }
      ], function(val) {
        if(val[0] === 'none') {
          element.css('display', 'flex');
          element.css(side, 0);
        } else if(val[0] === 'flex') {
          element.css('display', 'none');
          // todo
        } else if(val[0] === 'block') {
          element.css('display', 'flex');
          element.css(side, val[1]);
        }
      });
    }
  };
}





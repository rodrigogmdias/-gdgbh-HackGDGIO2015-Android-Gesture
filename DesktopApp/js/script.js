var GestureApp = angular.module('GestureApp', [])

.controller('AppController', function($scope){
  $scope.gestures = [
    {
      "name": "Proximo",
      "hotkey": "right-button",
    },
    {
      "name": "Anterior",
      "hotkey": "left-button",
    },
    {
      "name": "Play Music",
      "hotkey": "enter",
    },
    {
      "name": "Stop Music",
      "hotkey": "esc",
    }
  ];
  $scope.page_show = 'home';
  
  $scope.changePage = function(page){
    $scope.page_show = page;
  }
})
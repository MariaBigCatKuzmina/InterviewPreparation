angular.module('StudentsApp').controller('studentFormController', function ($http, $scope, $rootScope){
    const contextPath = 'http://localhost:8181/students/api/v1/students';

    $scope.submitStudent = function (){
        console.log('submit');
        console.log($scope.Student.name);
        $http.post(contextPath, $scope.Student).then(function (){
            window.location.replace('/students');
        });
        console.log($scope.Student.name);
    }
})
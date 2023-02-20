(function () {
    angular.module('StudentsApp', ['ngRoute']).config(config);

    function config($routeProvider) {
        $routeProvider
            .when('/students', {
                templateUrl: 'studentsList.html',
                controller: 'studentsListController'
            })
            .when('/student', {
                templateUrl: 'studentForm.html',
                controller: 'studentFormController'
            })
            .otherwise({redirectTo: '/students'});

    }
})();

angular.module('StudentsApp').controller('indexController', function (){
    console.log('index controller');
})



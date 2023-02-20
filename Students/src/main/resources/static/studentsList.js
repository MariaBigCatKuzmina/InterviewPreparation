angular.module('StudentsApp').controller('studentsListController', function ($scope, $http, $rootScope) {
    const contextPath = 'http://localhost:8181/students/api/v1/students';
    //   $scope.page = 0;
    $scope.loadStudents = function () {
        $http.get(contextPath, {
            params: {
                page: $scope.page
            }
        }).then(function (response) {
            $scope.studentsPage = response.data;
            $scope.pagesNumbersArray = $scope.generatePagesNumbersArray(response.data.number, 8, response.data.totalPages - 1)
        });
    }

    $scope.pagedStudents = function (pageNum) {
        $scope.page = pageNum;
        $scope.loadStudents();
    }

    $scope.generatePagesNumbersArray = function (currentPageNumber, visibleElementsCount, lastPageNumber) {
        let offset = Math.floor(visibleElementsCount / 2);
        visibleElementsCount = visibleElementsCount > lastPageNumber + 1 ? lastPageNumber + 1 : visibleElementsCount;
        let max = currentPageNumber + offset >= lastPageNumber ? lastPageNumber + 1 : currentPageNumber + offset + 1;
        let min = currentPageNumber - offset <= 0 ? 1 : currentPageNumber - offset + 1;
        if (max - min < visibleElementsCount - 1) {
            if (min > 1) {
                min = max - visibleElementsCount + 1;
            }
        }
        let pagesArray = new Array(visibleElementsCount).fill(1);
        for (let i = 0; i < visibleElementsCount; i++) {
            pagesArray[i] = min + i;
        }
        return pagesArray;
    }

    $scope.getById = function (id) {
        $http.get(contextPath + "/" + id).then(function (response) {
            if (response.data) {
                $scope.student = response.data
            }
        })
    }

    $scope.deleteById = function (id) {
        $http.delete(contextPath + "/" + id).then(function () {
            $scope.loadStudents()
        });
    }


    $scope.addStudent = function () {
        $rootScope.isNew = true;
        console.log('add student');
        window.location.replace('#!/student');
    }

    $scope.editStudent = function (id) {
        $rootScope.isNew = false;
        console.log('edit student');
        $http.get(contextPath + '/' + id).then(function (response) {
            if (response) {
                $rootScope.Student = response.data;
            }
        })
        window.location.replace('#!/student');

    }

    $scope.loadStudents();

})
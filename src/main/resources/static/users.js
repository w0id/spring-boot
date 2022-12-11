angular.module('app', []).controller('usersController', function ($scope, $document, $http) {
    const contextPath = 'http://localhost:9099/app/api/v1/'
    $scope.filter = {}

    $scope.deleteUser = function (userId) {
        $http.delete(contextPath + 'users/' + userId)
            .then(function (response) {
                $scope.setActive(true);
                $scope.loadUsers(1);
            });
    }

    $scope.addUser = function (form) {
        $http({
            url: contextPath + 'users',
            method: 'POST',
            data: {
                username: $scope.data.Username,
                password: $scope.data.Password,
                email: $scope.data.Email
            }
        })
            .then(function (response) {
            $scope.loadUsers($scope.page);
            $scope.data = {};
        });
    };

    $scope.loadUsers = function (page) {
        $scope.page = page;
        $http({
            url: contextPath + 'users',
            method: 'GET',
            params: {
                p: $scope.filter ? $scope.page : null
            }
        })
            .then(function (response) {
                $scope.usersList = response.data.content;
                $scope.page = response.data.pageable.pageNumber + 1;
                $scope.totalPages = response.data.totalPages;
                if ($scope.page > $scope.totalPages) {
                    $scope.page = $scope.totalPages -1;
                }
                $scope.count = response.data.content.length;
            });
    };

    $scope.setActive = function (static) {
            if (static == null) {
                var header = $document[0].getElementById("pagination");
                var btns = header.getElementsByClassName("page-item");
                for (var i = 0; i < btns.length; i++) {
                    btns[i].addEventListener("click", function () {
                        var current = $document[0].getElementsByClassName("active");
                        current[0].className = current[0].className.replace(" active", "");
                        this.className += " active";
                    });
                }
            } else {
                document.getElementsByClassName("active")[0].classList.toggle("active");
                document.getElementsByClassName("static")[0].classList.toggle("active");
            }
    }

    $scope.changePage = function (page) {
        $scope.setActive();
        $scope.loadUsers(page);
    }

    $scope.loadUsers(1);
}).controller('range', function ($scope, $document) {
    $scope.$parent.filter = $scope;
    $(".js-range-slider").ionRangeSlider({
        onFinish: function (data) {
            $scope.min = data.from
            $scope.max = data.to
            $scope.$parent.setActive(true);
            $scope.$parent.loadUsers(1);
        }
    });
})
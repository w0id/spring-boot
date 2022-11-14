angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:9099/app'

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductsList = response.data;
                $scope.count = response.data.length;
            });
    };

    $scope.deleteProduct = function (studentId) {
        $http.get(contextPath + '/products/delete/' + studentId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeCost = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'POST',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.addProduct = function (form) {
        $http({
            url: contextPath + '/products/add_product',
            method: 'POST',
            params: {
                name: $scope.data.Name,
                cost: $scope.data.Cost
            }
        }).then(function (response) {
            $scope.loadProducts();
            $scope.data = {};
        });
    }

    $scope.loadProducts();
})
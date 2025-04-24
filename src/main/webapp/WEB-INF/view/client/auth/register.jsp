<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
        />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Đăng ký - Laptopshop</title>
        <link href="/css/styles.css" rel="stylesheet" />
        <script
            src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
            crossorigin="anonymous"
        ></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div
                                    class="card shadow-lg border-0 rounded-lg mt-5"
                                >
                                    <div class="card-header">
                                        <h3
                                            class="text-center font-weight-light my-4"
                                        >
                                            Tạo tài khoản
                                        </h3>
                                    </div>
                                    <div class="card-body">
                                        <form:form
                                            method="post"
                                            action="/register"
                                            modelAttribute="registerUser"
                                        >
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div
                                                        class="form-floating mb-3 mb-md-0"
                                                    >
                                                        <c:set var="errorFirstName">
                                                            <form:errors
                                                                path="firstName"
                                                                class="invalid-feedback"
                                                            />
                                                        </c:set>
                                                        <form:input
                                                            class="form-control ${not empty errorFirstName ? 'is-invalid' : ''}"
                                                            id="inputFirstName"
                                                            type="text"
                                                            placeholder="Enter your first name"
                                                            path="firstName"
                                                        />
                                                        <label
                                                            for="inputFirstName"
                                                            >Tên</label
                                                        >
                                                        ${errorFirstName}
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <c:set var="errorLastName">
                                                            <form:errors
                                                                path="lastName"
                                                                class="invalid-feedback"
                                                            />
                                                        </c:set>
                                                        <form:input
                                                            class="form-control ${not empty errorLastName ? 'is-invalid' : ''}"
                                                            id="inputLastName"
                                                            type="text"
                                                            placeholder="Enter your last name"
                                                            path="lastName"
                                                        />
                                                        <label
                                                            for="inputLastName"
                                                            >Họ</label
                                                        >
                                                        ${errorLastName}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <c:set var="errorEmail">
                                                    <form:errors
                                                        path="email"
                                                        class="invalid-feedback"
                                                    />
                                                </c:set>
                                                <form:input
                                                    class="form-control ${not empty errorEmail ? 'is-invalid' : ''}"
                                                    id="inputEmail"
                                                    type="email"
                                                    placeholder="name@example.com"
                                                    path="email"
                                                />
                                                <label for="inputEmail"
                                                    >Email</label
                                                >
                                                ${errorEmail}
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div
                                                        class="form-floating mb-3 mb-md-0"
                                                    >
                                                        <c:set
                                                            var="errorPassword"
                                                        >
                                                            <form:errors
                                                                path="password"
                                                                class="invalid-feedback"
                                                            />
                                                        </c:set>
                                                        <form:input
                                                            class="form-control ${not empty errorPassword ? 'is-invalid' : ''}"
                                                            id="inputPassword"
                                                            type="password"
                                                            placeholder="Create a password"
                                                            path="password"
                                                        />
                                                        <label
                                                            for="inputPassword"
                                                            >Mật khẩu</label
                                                        >
                                                        ${errorPassword}
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div
                                                        class="form-floating mb-3 mb-md-0"
                                                    >
                                                        <c:set
                                                            var="errorConfirmPassword"
                                                        >
                                                            <form:errors
                                                                path="confirmPassword"
                                                                class="invalid-feedback"
                                                            />
                                                        </c:set>
                                                        <form:input
                                                            class="form-control ${not empty errorConfirmPassword ? 'is-invalid' : ''}"
                                                            id="inputPasswordConfirm"
                                                            type="password"
                                                            placeholder="Confirm password"
                                                            path="confirmPassword"
                                                        />
                                                        <label
                                                            for="inputPasswordConfirm"
                                                            >Xác nhận mật
                                                            khẩu</label
                                                        >
                                                        ${errorConfirmPassword}
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid">
                                                    <button
                                                        class="btn btn-primary btn-block"
                                                        type="submit"
                                                    >
                                                        Tạo tài khoản
                                                    </button>
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small">
                                            <a href="/login"
                                                >Bạn có tài khoản? Đi đến đăng
                                                nhập</a
                                            >
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"
        ></script>
        <script src="/js/scripts.js"></script>
    </body>
</html>

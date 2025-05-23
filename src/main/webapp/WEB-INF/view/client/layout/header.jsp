<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container-fluid fixed-top">
    <div class="container topbar bg-primary d-none d-lg-block">
        <div class="d-flex justify-content-between">
            <div class="top-info ps-2">
                <small class="me-3"
                    ><i class="fas fa-map-marker-alt me-2 text-secondary"></i>
                    <a
                        href="https://www.google.com/maps/search/11+Ng%C3%B5+139+Ph%E1%BB%91+T%C3%A2n+Mai,+T%C3%A2n+Mai,+Ho%C3%A0ng+Mai,+H%C3%A0+N%E1%BB%99i/@20.9841368,105.8490961,136m/data=!3m1!1e3?entry=ttu&g_ep=EgoyMDI1MDMwNC4wIKXMDSoASAFQAw%3D%3D"
                        class="text-white"
                        target="_blank"
                        >Số 11 Ngõ 139, Tân Mai, Hoàng Mai, Hà Nội</a
                    ></small
                >
                <small class="me-3"
                    ><i class="fas fa-envelope me-2 text-secondary"></i
                    ><a
                        href="https://mail.google.com/mail/u/0/?hl=en#inbox?compose=GTvVlcSBmmBcZjPBFkpvzmfpTwBgVlhsvmbPPQtlBNLhGNPtJXwzhhVjsTWpQwNpdVtpTtgVlDbkC"
                        class="text-white"
                        target="_blank"
                        >hailamtranvan@gmail.com</a
                    ></small
                >
            </div>
            <div class="top-link pe-2">
                <a href="#" class="text-white" target="_blank"
                    ><small class="text-white mx-2">Chính sách bảo mật</small
                    >/</a
                >
                <a href="#" class="text-white" target="_blank"
                    ><small class="text-white mx-2">Điều khoản sử dụng</small
                    >/</a
                >
                <a href="#" class="text-white" target="_blank">
                    <small class="text-white ms-2">
                        Bán hàng và hoàn tiền</small
                    >
                </a>
            </div>
        </div>
    </div>
    <div class="container px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="/" class="navbar-brand">
                <h1 class="text-primary display-6">Laptopshop</h1>
            </a>
            <button
                class="navbar-toggler py-2 px-3"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarCollapse"
            >
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div
                class="collapse navbar-collapse bg-white justify-content-between mx-5"
                id="navbarCollapse"
            >
                <div class="navbar-nav">
                    <a href="/" class="nav-item nav-link">Trang chủ</a>
                    <a href="/products" class="nav-item nav-link">Sản phẩm</a>
                    <a href="/admin" class="nav-item nav-link">Quản trị</a>
                </div>
                <div class="d-flex m-3 me-0">
                    <button
                        class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4"
                        data-bs-toggle="modal"
                        data-bs-target="#searchModal"
                    >
                        <i class="fas fa-search text-primary"></i>
                    </button>
                    <c:if test="${not empty pageContext.request.userPrincipal}">
                        <a href="/cart" class="position-relative me-4 my-auto">
                            <i class="fa fa-shopping-bag fa-2x"></i>
                            <span
                                class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                style="
                                    top: -5px;
                                    left: 15px;
                                    height: 20px;
                                    min-width: 20px;
                                "
                                id="sumCart"
                            >
                                <c:out value="${sessionScope.sum}" />
                            </span>
                        </a>
                        <div class="dropdown my-auto">
                            <a
                                href="#"
                                class="dropdown"
                                role="button"
                                id="dropdownMenuLink"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                            >
                                <i class="fas fa-user fa-2x"></i>
                            </a>

                            <ul
                                class="dropdown-menu dropdown-menu-end p-4"
                                aria-labelledby="dropdownMenuLink"
                            >
                                <li
                                    class="d-flex align-items-center flex-column"
                                    style="min-width: 300px"
                                >
                                    <img
                                        style="
                                            width: 150px;
                                            height: 150px;
                                            border-radius: 50%;
                                            overflow: hidden;
                                        "
                                        src="/images/avatar/${sessionScope.avatar}"
                                    />
                                    <div class="text-center my-3">
                                        <c:out
                                            value="${sessionScope.fullName}"
                                        />
                                    </div>
                                </li>

                                <li>
                                    <a class="dropdown-item" href="#"
                                        >Quản lý tài khoản</a
                                    >
                                </li>

                                <li>
                                    <a
                                        class="dropdown-item"
                                        href="/order-history"
                                        >Lịch sử mua hàng</a
                                    >
                                </li>
                                <li>
                                    <hr class="dropdown-divider" />
                                </li>
                                <li>
                                    <form method="post" action="/logout">
                                        <input
                                            type="hidden"
                                            name="${_csrf.parameterName}"
                                            value="${_csrf.token}"
                                        />
                                        <button class="dropdown-item">
                                            Đăng xuất
                                        </button>
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${empty pageContext.request.userPrincipal}">
                        <a href="/login" class="a-login position-relative me-4 my-auto">
                            Đăng nhập
                        </a>
                    </c:if>
                </div>
            </div>
        </nav>
    </div>
</div>
<!-- Modal Search Start -->
<div
    class="modal fade"
    id="searchModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
>
    <div class="modal-dialog modal-fullscreen">
        <div class="modal-content rounded-0">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                    Search by keyword
                </h5>
                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="modal"
                    aria-label="Close"
                ></button>
            </div>
            <div class="modal-body d-flex align-items-center">
                <div class="input-group w-75 mx-auto d-flex">
                    <input
                        type="search"
                        class="form-control p-3"
                        placeholder="keywords"
                        aria-describedby="search-icon-1"
                    />
                    <span id="search-icon-1" class="input-group-text p-3"
                        ><i class="fa fa-search"></i
                    ></span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal Search End -->

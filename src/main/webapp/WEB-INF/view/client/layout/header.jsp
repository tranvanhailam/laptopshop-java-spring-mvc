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
                <h1 class="text-primary display-6">Fruitables</h1>
            </a>
            <button
                class="navbar-toggler py-2 px-3"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarCollapse"
            >
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <div class="navbar-nav mx-auto">
                    <a href="/" class="nav-item nav-link active">Home</a>
                    <a href="shop.html" class="nav-item nav-link">Shop</a>
                    <a href="shop-detail.html" class="nav-item nav-link"
                        >Shop Detail</a
                    >
                    <div class="nav-item dropdown">
                        <a
                            href="#"
                            class="nav-link dropdown-toggle"
                            data-bs-toggle="dropdown"
                            >Pages</a
                        >
                        <div class="dropdown-menu m-0 bg-secondary rounded-0">
                            <a href="cart.html" class="dropdown-item">Cart</a>
                            <a href="chackout.html" class="dropdown-item"
                                >Chackout</a
                            >
                            <a href="testimonial.html" class="dropdown-item"
                                >Testimonial</a
                            >
                            <a href="404.html" class="dropdown-item"
                                >404 Page</a
                            >
                        </div>
                    </div>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                </div>
                <div class="d-flex m-3 me-0">
                    <button
                        class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4"
                        data-bs-toggle="modal"
                        data-bs-target="#searchModal"
                    >
                        <i class="fas fa-search text-primary"></i>
                    </button>
                    <a href="#" class="position-relative me-4 my-auto">
                        <i class="fa fa-shopping-bag fa-2x"></i>
                        <span
                            class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                            style="
                                top: -5px;
                                left: 15px;
                                height: 20px;
                                min-width: 20px;
                            "
                            >3</span
                        >
                    </a>
                    <a href="#" class="my-auto">
                        <i class="fas fa-user fa-2x"></i>
                    </a>
                </div>
            </div>
        </nav>
    </div>
</div>

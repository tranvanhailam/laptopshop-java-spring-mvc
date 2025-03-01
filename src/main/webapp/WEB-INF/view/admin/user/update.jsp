<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>

        <!-- Reset CSS -->
        <link rel="stylesheet" href="/css/reset.css" />

        <!-- Font Roboto -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
            rel="stylesheet"
        />

        <!-- <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
        />

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> -->

        <!-- Style CSS -->
        <link rel="stylesheet" href="/css/admin/user/update.css" />
    </head>
    <body>
        <main>
            <div class="container">
                <h1 class="header">Create a user</h1>
                <div class="line"></div>
                <form:form
                    method="post"
                    action="/admin/user/update"
                    modelAttribute="user"
                    class="form"
                >
                    <div class="field">
                        <label class="field__label">ID:</label>
                        <form:input
                            type="text"
                            class="field__input"
                            path="id"
                        />
                    </div>
                    <div class="field">
                        <label class="field__label">Email:</label>
                        <form:input
                            type="email"
                            class="field__input"
                            path="email"
                        />
                    </div>
                    <div class="field">
                        <label class="field__label">Phone number:</label>
                        <form:input
                            type="text"
                            class="field__input"
                            path="phoneNumber"
                        />
                    </div>
                    <div class="field">
                        <label class="field__label">Full Name:</label>
                        <form:input
                            type="text"
                            class="field__input"
                            path="fullName"
                        />
                    </div>
                    <div class="field">
                        <label class="field__label">Address:</label>
                        <form:input
                            type="text"
                            class="field__input"
                            path="address"
                        />
                    </div>
                    <input type="submit" value="Update" class="btn-submit" />
                </form:form>
            </div>
        </main>
    </body>
</html>

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
        <link rel="stylesheet" href="/css/admin/user/delete.css" />
    </head>
    <body>
        <main>
            <div class="container">
                <h1 class="header">
                    Delete the user with id ${user.id}, name ${user.name}
                </h1>
                <div class="line"></div>
                <p class="confirm-info">Are you sure delete this user ?</p>
                <form:form
                    method="post"
                    action="/admin/user/delete"
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
                    <input type="submit" value="Confirm" class="btn-submit" />
                </form:form>
            </div>
        </main>
    </body>
</html>

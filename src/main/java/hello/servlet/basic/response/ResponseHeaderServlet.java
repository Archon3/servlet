package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // status-line
        response.setStatus(HttpServletResponse.SC_OK);

        // response-header
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // header 편의메서드
        content(response);
        cookie(response);
        redirect(response);

        // message body
        PrintWriter writer = response.getWriter();
        writer.println("ok");

    }

    private void content(HttpServletResponse response) {
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(3); //생략 시 자동 생성
    }

     private void cookie(HttpServletResponse response) {
//         response.setHeader("Set-Cookie", "myCookie=good;Max-Age=600");
         Cookie cookie = new Cookie("myCookie", "good");
         cookie.setMaxAge(600); //600초
         response.addCookie(cookie);
     }

     private void redirect(HttpServletResponse response) throws IOException{
        //Status Code 302
        //Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND);
//        response.setHeader("Location", "basic/hello-firm.html");
        response.sendRedirect("/basic/hello-form.html");
     }
}

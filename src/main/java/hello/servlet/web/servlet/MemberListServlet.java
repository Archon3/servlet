package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<head>\n" +
                "   <meta charset=\"UTF-8\">\n" +
                "   <title>Title</title\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"/index.html\">메인</a>" +
                "<table>" +
                "   <thead>" +
                "   <th>id</th>" +
                "   <th>username</th>" +
                "   <th>age</th>" +
                "   </thead>" +
                "   <tbody>");

        for (Member member : members) {
            writer.write("   <tr>");
            writer.write("  <td>"+member.getId()+"</td>");
            writer.write("  <td>"+member.getUsername()+"</td>");
            writer.write("  <td>"+member.getAge()+"</td>");
            writer.write("   </tr>");
        }

        writer.write("   </tbody>" +
                "</table>" +
                "</body>\n" +
                "</html>\n");
    }
}

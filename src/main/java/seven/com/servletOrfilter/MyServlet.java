package seven.com.servletOrfilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenhaijun on 2017/1/5.
 *
 * 在spring boot中添加自己的Servlet有两种方法，代码注册Servlet和
 * 注解自动注册（Filter和Listener也是如此）。
 *
 *一、代码注册通过ServletRegistrationBean、 FilterRegistrationBean
 *  和 ServletListenerRegistrationBean 获得控制。
 *也可以通过实现 ServletContextInitializer 接口直接注册。
 *
 *
 *二、在 SpringBootApplication 上使用@ServletComponentScan注解后，
 * Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener
 * 注解自动注册，无需其他代码。
 *
 */
public class MyServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------------------进来了dopost()=,"+"当前类=MyServlet.doPost()");
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doPost()<<<<<<<<<<<");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>这是：MyServlet1</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}

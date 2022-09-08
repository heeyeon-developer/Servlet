package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);//http 첫라인 불러오기
        printHeaders(request);//Header 모든 정보 불러오기
        printHeaderUtils(request);//Header 편의 정보 불러오기
        printEtc(request);//기타 정보 불러오기
    }
    //http의 첫라인을 불러오는 코드
    private void printStartLine(HttpServletRequest request){
        System.out.println("--- REQUEST-LINE - START ---");

        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());

        System.out.println("request.getRequestURL() = " + request.getRequestURL());//localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());// /request-header

        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());//https 사용 유뮤
        System.out.println("--- REQUEST-LINE - END ---");

        System.out.println();
    }

    //header 모든 정보
    private void printHeaders(HttpServletRequest request){
        System.out.println("---Headers - START ---");


        /*Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            System.out.println(headerName + " : " + headerName);
        }*/

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + " : " + headerName));

//        request.getHeader("host")//원하는 헤더정보를 하나만 꺼낼 수 있음
        System.out.println("Headers - END ---");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest request){
        System.out.println("--- Header 편의 조회 START ---");
        System.out.println("[host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServerPort() = " + request.getServerPort());
        System.out.println();

        System.out.println("[Accept-Language 편의 조회");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale()" + request.getLocale());
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 END ---");
        System.out.println();
    }
    //기타 정보
    private void printEtc(HttpServletRequest request){
        System.out.println("--- 기타 조회 START ---");

        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());

        System.out.println("--- 기타 조회 END ---");
        System.out.println();
    }
}

#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 서블릿 컨텍스트와 리퀘스트 매핑은 이미 끝난 이후부터 설명하겠습니다.
* localhost:8080에 접근 시 디폴트 welcome file로 등록되어있는 index.jsp 파일을 호출하게 됩니다.
* 이 index.jsp 내부에서 "/list.next" 로 리다이렉트를 하고 있습니다.
* 서버는 다시 "/list.next"로 리퀘스트를 받습니다.
* url이 "*.next"인 요청에 대해 처리하는 servlet인 DispatcherServelet에서 service()함수가 실행됩니다. (이 전에 이 요청이 해당 서블릿의 처음 요청이었다면 init()함수가 실행되게 됩니다.)
* 해당 서블릿에서 uri를 분석해 RequestMapping에 따라 listController 를 꺼내옵니다.
* listController.execute를 하면 listController 에서 QuestionDao 객체를 호출해 DB에 있는 퀘스트들을 가져와 jstl view "list.jsp" 에 퀘스트값을 넘기고 해당 뷰를 리턴합니다.
* jstl view를 html화면으로 렌더링합니다. 
* 렌더링 된 화면을  http 통신으로 클라이언트에 내려줍니다.

#### 8. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 각 컨트롤러는 리퀘스트 매핑 시에 초기화가 이뤄지면서 단 하나의 객체만 생성됩니다.
* 따라서 excute 메소드가 아닌 전역에 존재하는 변수들 또한 하나의 값만 존재할 수 밖에 없습니다.
* 이 전역변수들은 멀티 쓰레드 상황에서 쓰레드들이 서로 자원을 공유하게 되는 영역이 됩니다.
* 한쪽이 수정하고 있는 와중에 다른 쪽에서 또 값을 수정하는 등 매우 혼란스러운 문제가 발생하게 됩니다.
* 따라서 전역에 있는 변수들을 excute 메소드안으로 옮김으로써 서로 자신만의 지역변수로 쓸 수 있도록 하면 문제는 해결됩니다. 


package huation.model.chat;



import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.nhncorp.mods.socket.io.SocketIOServer;
import com.nhncorp.mods.socket.io.SocketIOSocket;
import com.nhncorp.mods.socket.io.impl.DefaultSocketIOServer;
import com.nhncorp.mods.socket.io.spring.DefaultEmbeddableVerticle;

public class Chatbean extends DefaultEmbeddableVerticle {
	private static SocketIOServer io = null;

	@Override
	public void start(Vertx vertx) {
		int port = 12345;
		HttpServer server = vertx.createHttpServer();
		
		// 현재 컴퓨터(자기 자신)을 서버로 잡는다.
		io = new DefaultSocketIOServer(vertx, server);
		
		io.sockets().onConnection(new Handler<SocketIOSocket>() {
			public void handle(final SocketIOSocket socket) {
				
				// 채팅이 들어올 때마다 계속 실행
				socket.on("msg", new Handler<JsonObject>() {
					public void handle(JsonObject event) {	
						
//						System.out.println("handler ::: " +	event.putString("mmm", "1111"));
						System.out.println("handler ::: " + event.getString("msg"));
						System.out.println("handler ::: " + event.getString("id"));
						// 'response'가 main.jsp 파일의 socket.on을 찾아간다(보낼 곳의 이름)
						io.sockets().emit("response", event);
						io.sockets().emit("aaa", event);
					}
				});
				
			}
		});
		
		// 실행
		server.listen(port);
	}
}

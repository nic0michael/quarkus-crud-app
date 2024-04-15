package za.co.nico.app;



import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/message-api")
@OpenAPIDefinition(info = @Info(title = "Specification API", description = "Used to manage the loyalty API specifications", version = "0.0.1"), servers = {
		@Server(description = "Developer Server localhost", url = "http://localhost:8080/"),
		@Server(description = "Development Server", url = "http://specification-dev.loyaltyplus.aero/"),
		@Server(description = "Test Server", url = "https://specification-qa.loyaltyplus.aero/") },
		components = @Components(
				headers = {@Header(name="X-Page-Count",schema = @Schema(type = SchemaType.NUMBER, description = "The total number of elements in the list availible.")),
						@Header(name="X-Total-Count",schema = @Schema(type = SchemaType.NUMBER, description = "The total number of pages availible.")),
						@Header(name="links",schema = @Schema(implementation = String.class,type = SchemaType.ARRAY, description = "The links to the first, previous, next, last pages."))
						}
				))
public class MessageApplication extends Application { 
	

}

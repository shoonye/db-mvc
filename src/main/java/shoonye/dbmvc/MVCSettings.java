package shoonye.dbmvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mvcSettings")
public class MVCSettings {
	public @Value("${mvc.error.group.key}") String globalErrorKey;
	public @Value("${mvc.success.group.key}") String globalSuccessKey;
}

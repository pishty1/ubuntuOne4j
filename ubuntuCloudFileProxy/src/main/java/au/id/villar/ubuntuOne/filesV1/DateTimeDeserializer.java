package au.id.villar.ubuntuOne.filesV1;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;

import java.io.IOException;

class DateTimeDeserializer extends JsonDeserializer<DateTime> {

	@Override
	public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		return new DateTime(jsonParser.getText());
	}

}

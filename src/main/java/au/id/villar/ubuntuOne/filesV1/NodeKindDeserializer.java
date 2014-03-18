package au.id.villar.ubuntuOne.filesV1;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;

import java.io.IOException;

class NodeKindDeserializer extends JsonDeserializer<NodeKind> {

	@Override
	public NodeKind deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException {
		try {
		 return NodeKind.valueOf(jsonParser.getText().toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}

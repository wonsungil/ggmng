package enm.ytps.dto.customTargeting.mixIn;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.AsPropertyTypeDeserializer;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.google.api.ads.admanager.axis.v202005.AudienceSegmentCriteria;
import com.google.api.ads.admanager.axis.v202005.CmsMetadataCriteria;
import com.google.api.ads.admanager.axis.v202005.CustomCriteria;
import com.google.api.ads.admanager.axis.v202005.CustomCriteriaSet;

import java.io.IOException;

public class CustomCriteriaNodeDeserializer extends AsPropertyTypeDeserializer {
 
    public CustomCriteriaNodeDeserializer(JavaType bt, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
        super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
    }

    public CustomCriteriaNodeDeserializer(CustomCriteriaNodeDeserializer customCriteriaNodeDeserializer, BeanProperty prop) {
        super(customCriteriaNodeDeserializer, prop);
    }

    @Override
    public TypeDeserializer forProperty(final BeanProperty prop) {
        return (prop == _property) ? this : new CustomCriteriaNodeDeserializer(this, prop);
    }

    @Override
    public Object deserializeTypedFromObject(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.readValueAsTree();
        Class<?> subType =null;
        
        JsonNode audienceSegmentIds = node.get("audienceSegmentIds");
        JsonNode cmsMetadataValueIds = node.get("cmsMetadataValueIds");
        JsonNode valueIds = node.get("valueIds");
        JsonNode children = node.get("children");
        
        if (audienceSegmentIds != null) {
            subType = AudienceSegmentCriteria.class;
        } else if (cmsMetadataValueIds != null) {
            subType = CmsMetadataCriteria.class;
        } else if (valueIds != null) {
            subType = CustomCriteria.class;
        } else if (children != null) {
            subType = CustomCriteriaSet.class;
        }
        
        JavaType type = SimpleType.construct(subType);
        JsonParser jsonParser = new TreeTraversingParser(node, jp.getCodec());
        if (jsonParser.getCurrentToken() == null) {
            jsonParser.nextToken();
        }

        JsonDeserializer<Object> deser = ctxt.findContextualValueDeserializer(type, _property);
        return deser.deserialize(jsonParser, ctxt);
    }
}



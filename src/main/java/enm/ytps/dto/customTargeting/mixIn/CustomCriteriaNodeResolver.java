package enm.ytps.dto.customTargeting.mixIn;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;

import java.util.Collection;

public class CustomCriteriaNodeResolver extends StdTypeResolverBuilder {
    
    @Override
    public TypeDeserializer buildTypeDeserializer(final DeserializationConfig config,
                                                  final JavaType baseType,
                                                  final Collection<NamedType> subTypes) {
        return new CustomCriteriaNodeDeserializer(baseType, null, _typeProperty, _typeIdVisible, null);
    }
}

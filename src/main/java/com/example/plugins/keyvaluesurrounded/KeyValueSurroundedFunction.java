package com.example.plugins.keyvaluesurrounded;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;
import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.expressions.Expression;
import org.graylog.plugins.pipelineprocessor.ast.functions.Function;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;

public class KeyValueSurroundedFunction implements Function<Map> {

    public static final String NAME = "key_value_surrounded";
    private static final String PARAM1 = "value";
    private static final String PARAM2 = "surrounded_start";
    private static final String PARAM3 = "surrounded_end";
    private static final String PARAM4 = "kv_delimiter";

    private final ParameterDescriptor<String, String> valueParam1 = ParameterDescriptor
            .string(PARAM1)
            .description("The value to apply key value separator")
            .build();
    private final ParameterDescriptor<String, String> valueParam2 = ParameterDescriptor
            .string(PARAM2)
            .description("The start character of the surrounded")
            .build();
    private final ParameterDescriptor<String, String> valueParam3 = ParameterDescriptor
            .string(PARAM3)
            .description("The end character of the surrounded")
            .build();
    private final ParameterDescriptor<String, String> valueParam4 = ParameterDescriptor
            .string(PARAM4)
            .description("The delimiter character")
            .build();
    

    @Override
    public Object preComputeConstantArgument(FunctionArgs functionArgs, String s, Expression expression) {
        return expression.evaluateUnsafe(EvaluationContext.emptyContext());
    }

    @Override
    public Map evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
        final String target1 = valueParam1.required(functionArgs, evaluationContext);
        final String target2 = valueParam2.required(functionArgs, evaluationContext);
        final String target3 = valueParam3.required(functionArgs, evaluationContext);
        final String target4 = valueParam4.required(functionArgs, evaluationContext);
        
        final Map<String, Object> retMap = new HashMap<String,Object>();
        
        Pattern p = Pattern.compile("\\" + target2 + "([^" + target3 + "]+)\\" + target3);
        Matcher m = p.matcher(target1);

        while(m.find()) {
            String[] key_value = m.group(1).split(target4);
            if (key_value.length == 2) {
                String key = key_value[0];
                String value = key_value[1];
                if (!key.isEmpty() && !value.isEmpty() && !value.equals("null")) {
                	if (NumberUtils.isDigits(value)) {
                		retMap.put(key, Long.parseLong(value));
                	} else {
                		retMap.put(key, value);
                	}
                }            	
            }
        }
        
        return retMap;
    }

    @Override
    public FunctionDescriptor<Map> descriptor() {
        return FunctionDescriptor.<Map>builder()
                .name(NAME)
                .description("Returns a map with the key values")
                .params(valueParam1,valueParam2,valueParam3,valueParam4)
                .returnType(Map.class)
                .build();
    }

}

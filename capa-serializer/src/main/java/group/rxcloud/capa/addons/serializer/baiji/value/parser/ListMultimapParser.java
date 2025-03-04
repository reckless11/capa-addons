package group.rxcloud.capa.addons.serializer.baiji.value.parser;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import group.rxcloud.capa.addons.serializer.baiji.collect.KeyValuePair;
import group.rxcloud.capa.addons.serializer.baiji.value.StringValues;

public class ListMultimapParser implements ValueParser<ListMultimap<String, String>> {

    public static final ListMultimapParser DEFAULT = new ListMultimapParser();

    @Override
    public ListMultimap<String, String> parse(String value) {
        if (StringValues.isNullOrWhitespace(value))
            return null;

        value = value.trim();
        ListMultimap<String, String> listMultimap = ArrayListMultimap.create();
        String[] pairValues = value.trim().split(";");
        for (String pairValue : pairValues) {
            KeyValuePair<String, String> pair = StringValues.toKeyValuePair(pairValue);
            if (pair == null)
                continue;

            String[] values = pair.getValue().split(",");
            for (String item : values) {
                if (StringValues.isNullOrWhitespace(item))
                    continue;

                listMultimap.put(pair.getKey(), item.trim());
            }
        }

        return listMultimap.size() == 0 ? null : listMultimap;
    }

}
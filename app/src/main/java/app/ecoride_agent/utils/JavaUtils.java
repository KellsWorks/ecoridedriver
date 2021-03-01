package app.ecoride_agent.utils;

import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.PatternItem;
import java.util.Arrays;
import java.util.List;

public class JavaUtils {

    public List<PatternItem> getDotted(){
        return Arrays.asList(new Dot(), new Gap(20), new Dash(30), new Gap(20));
    }
}

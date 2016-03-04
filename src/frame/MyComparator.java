 package frame;

import java.util.Comparator;

public class MyComparator implements Comparator{

	
	@Override
public int compare(Object o1, Object o2) {
	Double i1 = Double.valueOf(o1.toString()).doubleValue();
	Double i2 = Double.valueOf(o2.toString()).doubleValue();
	
	if (i1 < i2){
		return 1;
	}else if (i1 > i2){
		return -1;
	}else {
		return 0;
	}

}
}

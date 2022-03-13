package gq.vapulite.Vapu.utils;

import java.util.ArrayList;

public class ModList<T> extends ArrayList<T> {
	
	public boolean needInterupt = true;
	@Override
	public boolean add(Object object) {
		boolean res = super.add((T) object);
		if (needInterupt) {
			Tool.logerror("mod add method");
			
			int index = -1;
			for(int i = 0; i < super.size(); i++) {
				if (super.get(i).getClass().getName().equals("com.xue.vapu.ClassTransformer")) {
					index = i;
					break;
				}
			}
			
			Tool.logerror("find index %s", index);
			
			Object myClassTransformer = null;
			if (index >= 0) {
				myClassTransformer = (Object) super.remove(index);
			}
			
			if (myClassTransformer != null) {
				super.add(super.size(), (T) myClassTransformer);
			}
			
			Tool.logerror("interupt add in mod list ");
			for(int i = 0; i < super.size(); i++) {
				Tool.logerror(super.get(i).toString());
			}
		}
		return res;
	}
}

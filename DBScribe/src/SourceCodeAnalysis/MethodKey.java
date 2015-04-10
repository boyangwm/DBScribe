package SourceCodeAnalysis;

public class MethodKey {
	// method name 
	public String key1funcName;
	
	//num of parameters
	public int key2NumPara;
	
	
	public MethodKey(String key1, int key2) {
		this.key1funcName = key1;
		this.key2NumPara = key2;
	}

	
	@Override   
	public boolean equals(Object o) {
		if (!(o instanceof MethodKey))
			return false;
		MethodKey ref = (MethodKey) o;
		if (!this.key1funcName.equals(ref.key1funcName))
			return false;
		if (!(this.key2NumPara == ref.key2NumPara))
			return false;

		return true;
	}

}

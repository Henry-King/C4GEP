package domain.core.inputmodel;

public abstract class FieldItem {
	private float value;
	private String name;
	public FieldItem(float value){
		this.value=value;
	}
	public FieldItem(){
		
	}
	public float getValue(){
		return value;
	}
	public void setValue(float value){
		this.value=value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

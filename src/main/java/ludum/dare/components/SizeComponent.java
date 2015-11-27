package ludum.dare.components;

import ludum.dare.interfaces.IComponent;

public class SizeComponent implements IComponent {
    public float w = 0;
    public float h = 0;

    public SizeComponent(){}
    public SizeComponent(float width, float height){
        this.w = width;
        this.h = height;
    }
}

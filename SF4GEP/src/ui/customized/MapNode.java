package ui.customized;

import java.awt.Color;

import twaver.ShapeNode;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import ui.Util;

public class MapNode extends ShapeNode {

    private int attachmentDirection = TWaverConst.ATTACHMENT_DIRECTION_TOP_RIGHT;

    public MapNode() {
        super();
        initStates();
    }

    public MapNode(Object id) {
        super(id);
        initStates();
    }

    public void initStates() {
        this.putBorderVisible(false);
        this.setShapeNodeType(TWaverConst.SHAPENODE_CLOSE_STRAIGHT_LINE);
        this.putCustomDrawFill(true);
        this.putCustomDrawOutline3D(false);
        this.putCustomDrawFillColor(TWaverUtil.getRandomAlphaColor());
        this.putCustomDrawOutlineStroke(TWaverConst.STROKE_SOLID_1);
        this.putCustomDrawGradient(false);
        this.putCustomDrawOutlineColor(Color.white);
        this.putCustomDrawOutline(true);
        this.putLabelPosition(TWaverConst.POSITION_CENTER);
        this.putLabelColor(Util.FONT_COLOR);
        this.putLabelFont(Util.FONT_11_PLAIN);
        this.putLabelHighlightable(false);
        this.putShapeNodeShowDashLine(false);
    }

    public int getAttachmentDirection() {
        return attachmentDirection;
    }

    public void setAttachmentDirection(int attachmentDirection) {
        if (this.attachmentDirection != attachmentDirection) {
            int oldValue = this.attachmentDirection;
            this.attachmentDirection = attachmentDirection;
            this.firePropertyChange("attachmentDirection", oldValue, attachmentDirection);
        }
    }
}

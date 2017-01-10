package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import constants.Constants.EAnchors;

public class Anchors extends Vector <Ellipse2D.Double>{
	private static final long serialVersionUID = 1L;
	public final static Integer ANCHORWIDTH = 8;
	public final static Integer ANCHORHEIGHT = 8;
	
	// 지금 좌표는 한개여야 하는 데 2개가 나옴(생성할 때 한번 그렸기 때문에). finishDrawing에서 그린다. 
	public Anchors() {
		for(int i=0; i<EAnchors.values().length; i++){
			this.add(new Ellipse2D.Double(0, 0, ANCHORWIDTH, ANCHORHEIGHT)); // anchor 9개가 벡터로 들어감 (0,0,4,4)으로 초기화 
		}
	}
	
	private void computeCoordinate(Rectangle r){
		for(int i=0; i<EAnchors.values().length; i++){
			switch(EAnchors.values()[i]){
			case NN:
				this.get(i).x = r.x+r.width/2-ANCHORWIDTH/2;
				this.get(i).y = r.y-ANCHORHEIGHT/2;
				break;
			case NE:
				this.get(i).x = r.x-ANCHORWIDTH/2;
				this.get(i).y = r.y-ANCHORHEIGHT/2; // 북동쪽 anchor의 y측 좌표 계산
				break;
			case NW:
				this.get(i).x = r.x+r.width-ANCHORWIDTH/2;
				this.get(i).y = r.y-ANCHORHEIGHT/2;
				break;
			case SS:
				this.get(i).x = r.x+r.width/2-ANCHORWIDTH/2;
				this.get(i).y = r.y+r.height-ANCHORHEIGHT/2;
				break;
			case SW:
				this.get(i).x = r.x-ANCHORWIDTH/2;
				this.get(i).y = r.y+r.height-ANCHORHEIGHT/2;
				break;
			case SE:
				this.get(i).x = r.x+r.width-ANCHORWIDTH/2;
				this.get(i).y = r.y+r.height-ANCHORHEIGHT/2;
				break;
			case EE:
				this.get(i).x = r.x+r.width-ANCHORWIDTH/2;
				this.get(i).y = r.y+r.height/2-ANCHORHEIGHT/2;
				break;
			case WW:
				this.get(i).x = r.x-ANCHORWIDTH/2;
				this.get(i).y = r.y+r.height/2-ANCHORHEIGHT/2;
				break;
			case RR:
				this.get(i).x = r.x+r.width/2-ANCHORWIDTH/2;
				this.get(i).y = r.y-ANCHORHEIGHT/2-40;
				break;
			default:
				break;
			}
		}
	}
	public void draw(Graphics2D g2d, Rectangle boundRectangle){
		computeCoordinate(boundRectangle); // anchors의 좌표 계산
		for(int i=0; i<EAnchors.values().length; i++){
			g2d.draw(this.get(i));
		}
	}
	
	public EAnchors contains(int x, int y){
		for(int i=0; i<EAnchors.values().length-1; i++){
			if(this.get(i).contains(x, y))
				return EAnchors.values()[i];
		}
		return null;
	}
}

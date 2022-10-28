import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Scanner;

public class TowerOfHanoi extends Application{

    private GraphicsContext gc;
    private MyTower<Integer> stack = new MyTower<Integer>();
    private MyTower<Integer> stackT2 = new MyTower<Integer>();
    private MyTower<Integer> stackT3 = new MyTower<Integer>();


    private final int MAXWIDTH = (500/4)-5;
    private final int MAXHEIGHT= 400;
    private final int MINWIDTH = 50;
    private final int MINHEIGHT = 20;

    private boolean seleuntower = false;
    private boolean seledostowe = false;
    private boolean seletrestoow = false;


    private boolean drawScreen = false;
    private Scanner scanner = new Scanner(System.in);
    private int diskAmount = 0;

    int numMoves = 0;
    boolean won =false;
    int diskWidthChange=0;
    int diskHeightChange =0;
    int diskHeight =0;
    final int totalHeight = 200;

    String selected1Tower = "";
    String selected2Tower = "";

    int count =1;


    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tower of Hanoi");

        Group group = new Group();

        Canvas canvas = new Canvas(500, 300);
        group.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        System.out.println("How many discs would you like to play with (3-7)?");
        diskAmount = scanner.nextInt();


        if(diskAmount>=3) {
            if (diskAmount <= 7) {
                {
                    diskHeight = totalHeight / diskAmount;

                    diskHeightChange = (MAXHEIGHT - MINHEIGHT) / diskAmount;
                    diskWidthChange = (MAXWIDTH - MINWIDTH) / diskAmount;
                    drawScreen = true;
                    for (int i = diskAmount; i > 0; i--) {
                        stack.push(i);
                    }
                    draw(gc);
                }
            }
        }
        else
        {
            System.out.println("Error Try Again\n");
            diskAmount = scanner.nextInt();
        }
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event)
            {
                if(count==4)
                {
                    count=1;
                }


                if(count==2) { count=3; }
                if(count==1)


                {


                    selected1Tower = event.getCode().getName();
                    if(selected1Tower.equals("1")&&!stack.empty()) { seleuntower=true;
                        draw(gc);
                        count=2;

                    }
                    else if(selected1Tower.equals("2")&&!stackT2.empty()) { seledostowe=true;
                        draw(gc);
                        count=2;
                    }
                    else if(selected1Tower.equals("3")&&!stackT3.empty()) {
                        seletrestoow=true;;
                        draw(gc);
                        count=2;
                    }


                    else { count=4;
                    }
                }


                if(count==3)
                {
                    selected2Tower = event.getCode().getName();
                    if(moveDisk(selected1Tower,selected2Tower))
                    {
                        numMoves++;

                        if (selected1Tower.equals("1") && selected2Tower.equals("2")) { seleuntower = true;stackT2.push(stack.pop());
                            draw(gc);
                            seleuntower = false;
                        } else if (selected1Tower.equals("1") && selected2Tower.equals("3")) { seleuntower = true;stackT3.push(stack.pop());
                            draw(gc);
                            seleuntower = false;
                        }


                        else if (selected1Tower.equals("2") && selected2Tower.equals("1")) { seledostowe = true;stack.push(stackT2.pop());
                            draw(gc);
                            seledostowe = false;
                        } else if (selected1Tower.equals("2") && selected2Tower.equals("3")) { seledostowe = true;stackT3.push(stackT2.pop());
                            draw(gc);
                            seledostowe = false;

                        }


                        else if (selected1Tower.equals("3") && selected2Tower.equals("2")) { seletrestoow = true;stackT2.push(stackT3.pop());
                            draw(gc);
                            seletrestoow = false;

                        } else if (selected1Tower.equals("3") && selected2Tower.equals("1")) { seletrestoow = true;stack.push(stackT3.pop());
                            draw(gc);
                            seletrestoow = false;
                        }
                        if(stack.empty()&&stackT2.empty()) {won=true;
                            drawScreen=false;
                        }
                    }
                    count = 4;


                    seleuntower = false;
                    seledostowe = false;
                    seletrestoow = false;


                    draw(gc);

                }
            }
        });
        Scene scene = new Scene(group);
        canvas.requestFocus();
        primaryStage.setScene(scene);
        draw(canvas.getGraphicsContext2D());
        primaryStage.show();
    }

    public int diswmeth(int rectValue)
    {
        int diskWidth = rectValue*diskWidthChange+MINWIDTH;
        return diskWidth;
    }


    public boolean moveDisk(String s, String t)
    {
        int topSourceDisk = 0;
        int topDestinationDisk=0;
        if(s!=t) {

            if (s.equals("1")) {
                topSourceDisk = stack.peek();

            }
            else if (s.equals("2")) { topSourceDisk = stackT2.peek();
            }




            else if (s.equals("3")) { topSourceDisk = stackT3.peek();
            }


            if (t.equals("1")&&!stack.empty()) {
                topDestinationDisk = stack.peek();
            } else if (t.equals("2")&&!stackT2.empty()) {
                topDestinationDisk = stackT2.peek();
            }




            else if (t.equals("3")&&!stackT3.empty()) {
                topDestinationDisk = stackT3.peek();
            }

            if(topDestinationDisk==0||topDestinationDisk>topSourceDisk)
            {
                return true;

            }
            else { return false;
            }




        }
        else
        {
            return false;
        }
    }



    public void draw(GraphicsContext g)
    {
        if(drawScreen==true)
        {

            g.setFill(Color.WHITE);


            g.fillRect(0,0,1000,1000);



            g.setFill(Color.BLACK);
            g.fillRect(0,280, 500, 20);




            g.setFill(Color.BLACK); int poleX=125;



            for(int i=1;i<=3;i++)
            {
                g.fillRect(((500/4)*i), 50, 10, 800);
            }



            if(seleuntower)
            {
                g.setFill(Color.RED);
                g.fillRect(((500/4)*1), 50, 10, 230);
            }


            if(seledostowe) { g.setFill(Color.RED);
                g.fillRect(((500/4)*2), 50, 10, 230);
            }



            if(seletrestoow)
            {
                g.setFill(Color.RED);
                g.fillRect(((500/4)*3), 50, 10, 230);
            }



            g.setFill(Color.BLUEVIOLET);



            for(int i = 0;i<stack.size();i++) {int rectX= poleX-(diswmeth(stack.get(i))-10)/2; int rectY = 280-(stack.size()-i)*diskHeight;

                g.strokeRect(rectX,rectY,diswmeth(stack.get(i)),diskHeight);
                g.fillRect(rectX,rectY,diswmeth(stack.get(i)),diskHeight);
            }

            for(int i = 0;i<stackT2.size();i++) {int rectX= poleX*2-(diswmeth(stackT2.get(i))-10)/2; int rectY = 280-(stackT2.size()-i)*diskHeight;


                g.strokeRect(rectX,rectY,diswmeth(stackT2.get(i)),diskHeight);g.fillRect(rectX,rectY,diswmeth(stackT2.get(i)),diskHeight);
            }

            for(int i = 0;i<stackT3.size();i++) { int rectX= poleX*3-(diswmeth(stackT3.get(i))-10)/2;
                int rectY = 280-(stackT3.size()-i)*diskHeight;  g.strokeRect(rectX,rectY,diswmeth(stackT3.get(i)),diskHeight);


                g.fillRect(rectX,rectY,diswmeth(stackT3.get(i)),diskHeight);

            }



        }

        if(won==true)
        {
            g.setFill(Color.RED);
            g.fillText("YOU WON IN "+numMoves+" moves",250,50);
        }
    }
}
package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	private static final int SZEROKOSC_SCENY = 400;
	private static final int WYSOKOSC_SCENY = 600;
	private static final int WYSOKOSC_KOSZYKA = 50;
	private static final int SZEROKOSC_KOSZYKA = 70;
	private static final int GRANICA_SPADANIA = WYSOKOSC_SCENY-WYSOKOSC_KOSZYKA;
	AnimationTimer timer;
	Pane pane = new Pane();
	List<Circle> jajka = new ArrayList<>();
	int totalIloscJajek = 0;
	double mouseX;
	Rectangle koszyk;
	double predkoscSpadania;
	double interwalTworzeniaJajka;
	Label lblUpuszczone;
	int upuszczone;

	public static void main(String[] args) {
		launch();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		lblUpuszczone = new Label("Upuszczone: 0");
		lblUpuszczone.setLayoutX(10);
		lblUpuszczone.setLayoutY(10);
		upuszczone = 0;

		predkoscSpadania = 1;
		interwalTworzeniaJajka = 2000;
				
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(interwalTworzeniaJajka), (event) -> {

			predkoscSpadania += interwalTworzeniaJajka / 4000;
			Circle circle = stworzNoweJajko();
			jajka.add(circle);
			pane.getChildren().add(circle);
			System.out.println("dodalem jajko");
		}));

		//ilosc wypuszczonych jajek
		timeline.setCycleCount(10);
		//timeline.play();
		
		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				gameUpdate();
//				koszyk.setLayoutX(mouseX);
			}
		};
		
		timer.start();
	
	
		

		koszyk = stworzNowyKoszyk();

		pane.getChildren().addAll(koszyk, lblUpuszczone);

		Scene scene = new Scene(pane, SZEROKOSC_SCENY, WYSOKOSC_SCENY);

		scene.setOnMouseMoved(e -> {
			mouseX = e.getX();
			//System.out.println("Wpolrzedna X myszy: " + mouseX + " " + Thread.currentThread().getName());
		});
		
		Button btnLogowanie = new Button("Podaj Imię");
		btnLogowanie.setLayoutX(SZEROKOSC_SCENY-100);
		btnLogowanie.setLayoutY(10);
		pane.getChildren().add(btnLogowanie);
		btnLogowanie.setOnAction(event-> new MenuLogowanie());
		
		Button btnStart = new Button("Uruchom Gre");
		btnStart.setLayoutX(SZEROKOSC_SCENY-100);
		btnStart.setLayoutY(50);
		pane.getChildren().add(btnStart);
		btnStart.setOnAction(event-> timeline.play());
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	
	public Circle stworzNoweJajko() {
		Circle jajko = new Circle();
		jajko.setLayoutX(rand(0, SZEROKOSC_SCENY));
		jajko.setLayoutY(1);
		jajko.setRadius(6);
		jajko.setFill(Color.BLUE);
		return jajko;
	}

	public Rectangle stworzNowyKoszyk() {
		Rectangle koszyk = new Rectangle();
		koszyk.setLayoutX(SZEROKOSC_SCENY/2);
		koszyk.setLayoutY(WYSOKOSC_SCENY-WYSOKOSC_KOSZYKA);
		koszyk.setHeight(WYSOKOSC_KOSZYKA);
		koszyk.setWidth(SZEROKOSC_KOSZYKA);
		koszyk.setFill(Color.DARKSLATEBLUE);
		return koszyk;

	}

	public int rand(int min, int max) {
		return (int) (Math.random() * max + min);
	}

	
	
	public void gameUpdate() {

		koszyk.setLayoutX(mouseX);

		for (int i = 0; i < jajka.size(); i++) {
			 Circle jajko = jajka.get(i);
			jajko.setLayoutY(jajko.getLayoutY() + predkoscSpadania );
			// if gdy wpada do koszyka
			if (jestZlapane(jajko)	) {
				pane.getChildren().remove( jajko);
				jajka.remove(i);
				System.out.println("-------------zlapane jajko");
				sprawdzCzyKoniec();
			}

			// if omija koszyk , upuszczone
			else if ( jestUpuszczone(jajko) ) {
				pane.getChildren().remove( jajko);
				jajka.remove(i);
				upuszczone ++;
				lblUpuszczone.setFont(Font.font ("Verdana", 20));
				lblUpuszczone.setTextFill(Color.RED);
				lblUpuszczone.setText("Upuszczone: " + String.valueOf(upuszczone));
				System.out.println("upuszczone jajko");
				sprawdzCzyKoniec();
			}

		}
		
	}

	private void sprawdzCzyKoniec() {
		totalIloscJajek++;
		if(totalIloscJajek == 10) {
			timer.stop();
			System.out.println("Koniec!");
			//graficznie zapytaj o imie
			Gracz gracz1 = new Gracz("gracz 1" , totalIloscJajek-upuszczone);
			PolaczenieDoSerwera polaczenieDoSerwera = new PolaczenieDoSerwera();
			try {
				polaczenieDoSerwera.wyslijWynik(gracz1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private boolean jestUpuszczone(Circle jajko) {
		return jajko.getLayoutY() > GRANICA_SPADANIA+WYSOKOSC_KOSZYKA;
	}

	private boolean jestZlapane(Circle jajko) {
		return (jajko.getLayoutX() > koszyk.getLayoutX() &&  jajko.getLayoutX() < koszyk.getLayoutX() + SZEROKOSC_KOSZYKA)
				&&  jajko.getLayoutY() >= GRANICA_SPADANIA;
	}

}
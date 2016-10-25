
package com.badlogic.invaders.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.invaders.Invaders;


public class MainMenu extends InvadersScreen {
	/** the SpriteBatch used to draw the background, logo and text **/
	private final SpriteBatch spriteBatch;
	/** the background texture **/
	private final Texture background;
	/** the logo texture **/
	private final Texture logo;
	/** the font **/
	private final BitmapFont font;
	/** is done flag **/
	private boolean isDone = false;
	/** view & transform matrix **/
	private final Matrix4 viewMatrix = new Matrix4();
	private final Matrix4 transformMatrix = new Matrix4();

	private final GlyphLayout glyphLayout = new GlyphLayout();

	public MainMenu (Invaders invaders) {
		super(invaders);

		spriteBatch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("data/planet1.jpg"));
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		logo = new Texture(Gdx.files.internal("data/title.png"));
		logo.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		font = new BitmapFont(Gdx.files.internal("data/font16.fnt"), Gdx.files.internal("data/font16.png"), false);

		if (invaders.getController() != null) {
			invaders.getController().addListener(new ControllerAdapter() {
				@Override
				public boolean buttonUp(Controller controller, int buttonIndex) {
					controller.removeListener(this);
					isDone = true;
					return false;
				}
			});
		}
	}

	@Override
	public boolean isDone () {
		return isDone;
	}

	@Override
	public void update (float delta) {
		if (Gdx.input.justTouched()) {
			isDone = true;
		}
	}

	@Override
	public void draw (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		viewMatrix.setToOrtho2D(0, 0, 480, 320);
		spriteBatch.setProjectionMatrix(viewMatrix);
		spriteBatch.setTransformMatrix(transformMatrix);
		spriteBatch.begin();
		spriteBatch.disableBlending();
		spriteBatch.setColor(Color.WHITE);
		spriteBatch.draw(background, 0, 0, 480, 320, 0, 0, 512, 512, false, false);
		spriteBatch.enableBlending();
		spriteBatch.draw(logo, 0, 320 - 128, 480, 128, 0, 0, 512, 256, false, false);
		spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		glyphLayout.setText(font, "Touch screen to start!");
		font.draw(spriteBatch, glyphLayout, 240 - glyphLayout.width / 2, 128);
		if (Gdx.app.getType() == ApplicationType.WebGL) {
			glyphLayout.setText(font, "Press Enter for Fullscreen Mode");
			font.draw(spriteBatch, glyphLayout, 240 - glyphLayout.width / 2, 128 - font.getLineHeight());
		}
		spriteBatch.end();
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		background.dispose();
		logo.dispose();
		font.dispose();
	}
}

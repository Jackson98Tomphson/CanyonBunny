package com.packtpub.libgdx.canyonbunny;

import com.badlogic.gdx.ApplicationListener;
import com.packtpub.libgdx.canyonbunny.game.WorldController;
import com.packtpub.libgdx.canyonbunny.game.WorldRenderer;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.assets.AssetManager;
import com.packtpub.libgdx.canyonbunny.game.Assets;


public class CanyonBunnyMain implements ApplicationListener {
	//	only used for the android platform:
	private boolean paused;
	private static final String TAG = CanyonBunnyMain.class.getName();
	private WorldController worldController;
	private WorldRenderer worldRenderer;

	@Override
	public void create() {
		// Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Load assets
		Assets.instance.init(new AssetManager());
		// Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		// Game world is active on start
		paused = false;

	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);

	}

	@Override
	public void render() {
		// Do not update game world when paused.
		if (!paused) {
			// Update game world by the time that has passed
			// since last rendered frame.
			worldController.update(Gdx.graphics.getDeltaTime());
		}
		// Sets the clear screen color to: Cornflower Blue
		Gdx.gl.glClearColor(100 / 255.0f, 149 / 255.0f, 237 / 255.0f, 255 / 255.0f);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Render game world to screen
		worldRenderer.render();
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
		// reload the assets as in the create() section
		Assets.instance.init(new AssetManager());
	}

	@Override
	public void dispose() {

		worldRenderer.dispose();
		Assets.instance.dispose();
	}
}

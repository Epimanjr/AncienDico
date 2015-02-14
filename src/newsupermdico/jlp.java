/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsupermdico;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;
import newsupermdico.donnees.Donnees;

/**
 *
 * @author Maxime BLAISE
 */
public class jlp implements Runnable {

    private String fFilename = null;
    private boolean remote = false;
    public static Player player;

    static public jlp createInstance(String[] args) {
        jlp p = new jlp();
        if (!p.parseArgs(args)) {
            p = null;
        }
        return p;
    }

    public jlp() {
    }

    public jlp(String filename) {
        init(filename);
    }

    public final void init(String filename) {
        fFilename = filename;
    }

    protected boolean parseArgs(String[] args) {
        boolean parsed = false;
        if (args.length == 1) {
            init(args[0]);
            parsed = true;
            remote = false;
        } else if (args.length == 2) {
            if (!(args[0].equals("-url"))) {
                showUsage();
            } else {
                init(args[1]);
                parsed = true;
                remote = true;
            }
        } else {
            showUsage();
        }
        return parsed;
    }

    public void showUsage() {
        System.out.println("Usage: jlp [-url] <filename>");
        System.out.println("");
        System.out.println(" e.g. : java javazoom.jl.player.jlp localfile.mp3");

    }

    /**
     * Retourne la position du player -1 si erreur
     *
     * @return la position
     */
    public int getPosition() {
        return player.getPosition();
    }

    public void stop() throws JavaLayerException {
        try {
            InputStream in = null;
            if (remote == true) {
                try {
                    in = getURLInputStream();
                } catch (Exception e) {
                }
            } else {
                in = getInputStream();
            }
            AudioDevice dev = getAudioDevice();
            Player p = new Player(in, dev);
            p.close();
        } catch (IOException ex) {
        }

    }

    public void play()
            throws JavaLayerException {
        try {
            if (Donnees.isModeVerbeux()) {
                System.out.println("playing " + fFilename + "...");
            }
            InputStream in;
            if (remote == true) {
                in = getURLInputStream();
            } else {
                in = getInputStream();
            }
            AudioDevice dev = getAudioDevice();
            player = new Player(in, dev);
            player.play();
        } catch (IOException ex) {
            throw new JavaLayerException("Problem playing file " + fFilename, ex);
        } catch (Exception ex) {
            throw new JavaLayerException("Problem playing file " + fFilename, ex);
        }
    }

    public void play(int i)
            throws JavaLayerException {

        try {
            System.out.println("playing " + fFilename + "...");
            InputStream in;
            if (remote == true) {
                in = getURLInputStream();
            } else {
                in = getInputStream();
            }
            AudioDevice dev = getAudioDevice();

            if (player != null) {
                player.close();
            }
            player = new Player(in, dev);

            System.out.println("play(i) avec i=" + i);
            if (i == 1) {
                player.play();
            } else {
                player.play(i);
            }

        } catch (IOException ex) {
            throw new JavaLayerException("Problem playing file " + fFilename, ex);
        } catch (Exception ex) {
            throw new JavaLayerException("Problem playing file " + fFilename, ex);
        }
    }

    /**
     * Jouer fichier de l' URL (Streaming).
     *
     * @return ...
     * @throws java.lang.Exception exception
     */
    protected InputStream getURLInputStream() throws Exception {

        URL url = new URL(fFilename);
        InputStream fin = url.openStream();
        BufferedInputStream bin = new BufferedInputStream(fin);
        return bin;
    }

    /**
     * Jouer un fichier de FileInputStream.
     *
     * @return ...
     * @throws java.io.IOException exception
     */
    protected InputStream getInputStream() throws IOException {
        FileInputStream fin = new FileInputStream(fFilename);
        BufferedInputStream bin = new BufferedInputStream(fin);
        return bin;
    }

    protected AudioDevice getAudioDevice() throws JavaLayerException {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.play();
            } catch (JavaLayerException e) {
                break;
            }
        }
    }

    public static void main(String[] ar) {
        jlp mp = new jlp();
        mp.init("/home/blaise/Musique/eminem-we.made.you.mp3");
        try {
            mp.play();
        } catch (JavaLayerException e) {
        }
    }
}

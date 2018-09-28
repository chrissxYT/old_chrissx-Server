package de.chrissx.bes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import de.chrissx.server.blockedit.editactions.EditAction;
import de.chrissx.server.blockedit.editactions.EditActionBiomes;
import de.chrissx.server.blockedit.editactions.EditActionBlocks;
import de.chrissx.server.file.FileWriter;
import de.chrissx.server.reflectors.Reflector;

public class Saver {

	public static void saveActions(Reflector refl, File file) throws IOException {
		if(!file.exists()) {
			Files.createFile(file.toPath());
		}
		List<String> write = new ArrayList<String>();
		for(EditAction ea : refl.getActions()) {
			write.addAll(besCreate(ea));
		}
		FileWriter.write(file, write, false);
		refl.clearActions();
	}
	
	private static List<String> besCreate(EditAction e) {
		List<String> out = new ArrayList<String>();
		String l1 = e.getPlayer();
		String l2 = e.getType().toString();
		String l3 = e.getStartLocation().getBlockX()+" "+e.getStartLocation().getBlockY()+" "+e.getStartLocation().getBlockZ()+" "+e.getStartLocation().getWorld().getName();
		String l4 = e.getEndLocation().getBlockX()+" "+e.getEndLocation().getBlockY()+" "+e.getEndLocation().getBlockZ()+" "+e.getEndLocation().getWorld().getName();
		String l5 = "";
		String l6 = Long.toString(e.getTime().getTime());
		if(e instanceof EditActionBlocks) {
			l5 = de.chrissx.cbb.Compressor.compress(((EditActionBlocks) e).getBefore());
		}else if(e instanceof EditActionBiomes) {
			l5 = de.chrissx.cbm.Compressor.compress(((EditActionBiomes)e).getBefore());
		}else {
			l5 = "ERROR AT " + Saver.class.getName() + ":69-74";
		}
		out.add(l1);
		out.add(l2);
		out.add(l3);
		out.add(l4);
		out.add(l5);
		out.add(l6);
		return out;
	}
}
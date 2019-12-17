package no.systema.main.validator;

/**
 * Checks file extensions. Bad or good.
 * @author oscardelatorre
 * @date Dec - 2019
 *
 */
public class FileExtensionValidator {
	
	private static final String[] INVALID_UPLOAD_FILE_TYPES = {".adp", ".app",".asp",".bas", ".bat", ".cer", ".chm",".cmd", 
			".cnt",".com",".cpl",".crt",".csh",".der",".exe",".fxp",".gadget",".hlp",".hpj",".hta",".inf",".ins",
			".isp",".its",".js",".jse",".ksh",".lnk",".mad",".maf",".mag",".mam",".maq",".mar",".mas",".mat",".mau",
			".mav",".maw",".mda",".mdb",".mde",".mdt", ".mdw",".mdz",".msc",".msh",".msh1",".msh2",".mshxml",".msh1xml",".msh2xml",
			".msi",".msp",".mst",".ops",".osd",".pcd",".pif",".plg",".prf",".prg",".pst",".reg",".scf",".scr",".sct",".shb",".shs",
			".ps1",".ps1xml",".ps2",".ps2xml",".psc1",".psc2",".tmp",".url",".vb",".vbe",".vbp",".vbs",".vsmacros",".vsw",
			".ws",".wsc",".wsf",".wsh",".xnk",".ade",".cla",".class",".grp",".jar",".mcf",".ocx",".pl",".xbap" };
	
	/**
	 * 
	 * @param fileName
	 * @return true if file is valid for upload; false if file is invalid
	 */
	public boolean isValidFileForUpload(String fileName){
		boolean retval = true;
		if(fileName!=null){
			for (int i = 0; i < INVALID_UPLOAD_FILE_TYPES.length; i++) { 
				if(fileName.contains(INVALID_UPLOAD_FILE_TYPES[i])){
					retval = false;
					break;
				}
			}
		}else{
			retval = false;
		}
	 	return retval;	    	    
	}
	
}

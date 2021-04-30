class File {
	boolean isFile;
	Map<String, File> children;
	File parent;
	String name;
	String content;
	Date createdAt;
	Extension extension;
	
	public File() {
		children = new HashMap<>();
		isFile = false;
		content = "";
		createdAt = new Date();
	}
	
	public File(String name) {
		this();
		this.name = name;
	}
	
	public void rename(String name) {
		parent.children.remove(this.name);
		this.name = name;
		parent.addChild(this.name, this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addChild(File f) {
		f.parent = this;
		children.put(f.getName(), f);
	}

	public void removeChild(String s) {
		return children.get(s);
	}
	
	public File getChild(String s) {
		return children.get(s);
	}
	
	public List<File> getChildren() {
		return new ArrayList<>(children.values());
	}
	
	public boolean contains(String s) {
		return children.containsKey(s);
	}
	
	public void addContent(String s) {
		this.content += s;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setFile() {
		this.isFile = true;
	}
	
	public boolean isFile() {
		return isFile;
	}
	
	public void delete() {
		parent.removeChild(name);
	}
}

public class FileSystem {
	File root;
	public FileSystem() {
		root = new File();
	}
	
	public List<String> ls(String path) {
		File current = traverse(path);
		List<String> result = new ArrayList<>();
		if(current.isFile) {
			result.add(current.getName());
		} else {
			for(File f: current.getChildren()) {
				result.add(f.getName());
			}
		}
		
		Collections.sort();
		return result;
	}
	
	//find all files in the given path
	public File traverse(String path) {
		File current = root;
		String[] paths = path.split("/");
		for(String p: paths) {
			if(p.isEmpty()) 
				continue;
			if(!current.contains(p)) {
				File f = new File(p);
				current.addChild(f);
			}
			current = current.getChild(p);
		}
		return current;
	}
	
	public List<String> find(String path) {
		File current = traverse(path);
		return find(current);
	}
	
	public List<String> find(File f) {
		List<String> res = new ArrayList<>();
		if (f == null) {
    		return res;
    	} else if (f.isFile()) {
    		res.add(f.getName());
    	} else {
    		for (File child : f.getChildren()) {
    			res.addAll(find(child));
    		}
    	}
    	return res;
	}
	
	public void rename(String path, String name) {
		File cur = traverse(path);
		cur.rename(name);
    }
	
	public void delete(String path) {
    	File cur = traverse(path);
		cur.delete();
    }
    
    public void mkdir(String path) {
    	File cur = traverse(path);
    }
    
    public void addContentToFile(String path, String content) {
    	File cur = traverse(path);
		cur.setFile();
		cur.addContent(content);
    }
    
    public String readContentFromFile(String path) {
    	File cur = traverse(path);
		return cur.getContent();
    }

    public static void main(String[] args) {
    	FileSystem fs = new FileSystem();
    	fs.mkdir("/a/b/c/d");
    	fs.addContentToFile("/a/b/c/d/e", "Hello");
    	fs.addContentToFile("/a/b/c/d/f", "Hello world");
    	fs.addContentToFile("/a/b/c/d/g/h", "Hello world 2");
    	fs.rename("/a/b/c/d/e", "newname");
    	for (String s : fs.find("/")) {
    		System.out.println(s);
    	}
    }
}




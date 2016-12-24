package com.niit.CB.DAO;

import java.util.List;
import com.niit.CB.model.Blog;

public interface BlogDAO {
	public Blog get(String blog_id);
	public boolean save(Blog blog);

	public List<Blog> getList();

}

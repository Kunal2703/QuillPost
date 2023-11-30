import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { loadAllPosts } from '../services/post-service'
import {Row, Col, Pagination, PaginationItem, PaginationLink} from 'reactstrap'
import {Container} from "reactstrap"
import {toast} from 'react-toastify'
import Post from './Post'

function NewFeed() {

  const [postContent, setPostContent] = useState({
    content: [],
    totalPages: '',
    totalElements: '',
    pageSize: '',
    lastPage: false,
    pageNumber: ''

  })

  useEffect(() => {

    changePage(0)

  },[])

  const changePage = (pageNumber = 0, pageSize = 5) => {
    if(pageNumber > postContent.pageNumber && postContent.lastPage) {
      return;
    }

    if(pageNumber < postContent.pageNumber && postContent.pageNumber===0) {
      return;
    }
    loadAllPosts(pageNumber, pageSize).then((data) => {
      setPostContent(data)
      console.log(data);
      window.scroll(0,0)
    }).catch(error => {
      toast.error("Error in loading Posts")
    })
  }

  return (

    <div className="container-fluid">
      <Row>
        <Col md = {
                { 
                    size: 10,
                    offset: 1
                }
            }>
              <h1>Total Number of Blog Post -  {postContent?.totalElements}</h1>

                {
                  postContent.content.map((post) => (
                    <Post post = {post} key={post.postId} />
                  ))
                }

                <Container className = 'text-center mt-3'>
                  <Pagination>
                    <PaginationItem onClick={() => changePage(postContent.pageNumber-1)} disabled = {postContent.pageNumber === 0}>
                      <PaginationLink previous>
                          prev
                      </PaginationLink>
                    </PaginationItem>

                    {
                        [...Array(postContent.totalPages)].map((item, index) => (
                          <PaginationItem onClick={()=> changePage(index)} active = {index === postContent.pageNumber} key={index}>
                              <PaginationLink>
                                {index + 1}
                              </PaginationLink>
                          </PaginationItem>
                        ))
                    }

                    <PaginationItem onClick={() => changePage(postContent.pageNumber+1)} disabled = {postContent.lastPage}>
                      <PaginationLink next>
                          next
                      </PaginationLink>
                    </PaginationItem>
                  </Pagination>
                </Container>

        </Col>
      </Row>
    </div>
    
  )
}

export default NewFeed
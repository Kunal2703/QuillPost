import { useEffect } from 'react'
import { useState } from 'react'
import Base from '../components/Base'
import {Link, useParams} from "react-router-dom"
import {Row, Col, Container} from "reactstrap"
import { loadPost } from '../services/post-service'
import { createComment } from '../services/post-service'

import {toast} from "react-toastify"
import { Card, CardBody, CardText, Input, Button} from 'reactstrap';
const PostPage = () => {


    const {postId} = useParams()
    const [post, setPost] = useState(null)
    const [comment, setComment] = useState({
        content: ''
    })

    useEffect(() => {
        //load post of postId
        loadPost(postId).then(data => {
            console.log(data);
            setPost(data)
        }).catch(error => {
            console.log(error)
            toast.error("Error in loading post")
        })
    }, [])

    const printDate = (number) => {
        return new Date(number).toLocaleDateString()
    }

    const submitPost = () => {
        createComment(comment, post.postItem.postId)
        .then(data => {
            console.log(data)
        }).catch(error => {
            console.log(error)
        })
    }

  return (
    <Base>
        <div>
            <Container className="mt-4">
                    <Link to = "/">Home</Link> / {post && (<Link to = "">{post.postItem.title}</Link>)}
                    <Row>
                        <Col md = {{
                            size: 12
                        }}> 

                            <div>
                                <Card className="mt-3 ps-2 border-0 shadow-sm" >
                                    {
                                        (post) && (
                                            <CardBody>
                                                <CardText> Posted By <b>{post.postItem.username}</b> on <b>{ printDate(post.postItem.date)}</b> </CardText>

                                                    <CardText>
                                                        <span className="text-muted">Category: {post.postItem.categoryName}</span>
                                                    </CardText>

                                                    <div className="divder" style={{
                                                        width: '100%',
                                                        height: '1px',
                                                        background: '#e2e2e2'
                                                    }}></div>

                                                    <CardText className="mt-3">
                                                        <h1>{post.postItem.title}</h1>
                                                    </CardText>
                                                    

                                                    <CardText dangerouslySetInnerHTML={{__html: post.postItem.content}}>

                                                    </CardText>
                                            </CardBody>
                                        )
                                    }
                                </Card>
                            </div>

                        </Col>
                    </Row>

                    <Row className="my-4">
                        <Col md = {
                            {
                                size: 9,
                                offset: 1
                            }
                        }>
                            <h3>Comments({post ? post.comments.length: 0})</h3>
                            {
                                post && post.comments.map((c, index )=>(
                                    <Card className = "mt-4 border-0" key = {index}>
                                        <CardBody>
                                            <CardText>
                                                {c.content}
                                            </CardText>
                                        </CardBody>
                                    </Card>
                                ))
                            }

                            <Card className = "mt-4 border-0">
                                <CardBody>

                                    <Input 
                                        type="textarea" 
                                        placeholder="Enter comment here..."
                                        value={comment.comment}
                                        onChange={(event) =>setComment({content: event.target.value})}
                                    />

                                    <Button onClick={submitPost} className="mt-2" color="primary">Submit</Button>

                                        
                                </CardBody>
                            </Card>



                        </Col>
                    </Row>

            </Container>
        </div>
    </Base>
  )
}

export default PostPage